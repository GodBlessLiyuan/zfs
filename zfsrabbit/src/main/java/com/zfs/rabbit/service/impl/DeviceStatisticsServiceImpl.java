package com.zfs.rabbit.service.impl;

import com.zfs.common.utils.RedisKeyUtil;
import com.zfs.rabbit.mapper.DeviceMapper;
import com.zfs.rabbit.mapper.DeviceStatisticsMapper;
import com.zfs.rabbit.pojo.DevicePO;
import com.zfs.rabbit.pojo.DeviceStatisticsPO;
import com.zfs.rabbit.service.DeviceStatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author: dangyi
 * @date: Created in 16:51 2019/11/14
 * @version: 1.0.0
 * @description:
 */
@Service
public class DeviceStatisticsServiceImpl implements DeviceStatisticsService {
    private final static Logger logger = LoggerFactory.getLogger(DeviceStatisticsServiceImpl.class);

    @Autowired
    private DeviceStatisticsMapper deviceStatisticsMapper;

    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private StringRedisTemplate template;

    /**
     * 根据用户设备访问信息，统计用户活跃数据
     * @param deviceInfo
     */
    @Override
    public void deviceStatic(Map<String, Object> deviceInfo) {
        // 解析出用户设备访问信息
        Long deviceId = (Long) deviceInfo.get("deviceId");
        Date visitTime = (Date) deviceInfo.get("visitTime");
        String ip = (String) deviceInfo.get("ip");

        //查询设备
        DevicePO devicePO = this.deviceMapper.selectByPrimaryKey(deviceId);
        //设备须真实存在
        if (null != devicePO) {
            // 插入数据库
            DeviceStatisticsPO po = new DeviceStatisticsPO();
            po.setDeviceId(deviceId);
            po.setVisitTime(visitTime);
            po.setIp(ip);
            this.deviceStatisticsMapper.insert(po);

            // 统计
            int dayActiveUser = this.deviceStatisticsMapper.queryDayActiveUser();
            int monthActiveUser = this.deviceStatisticsMapper.queryMonthActiveUser();

            // 将统计结果封装成map
            Map<String, String> deviceStatistics = new HashMap();
            deviceStatistics.put("dayActiveUser", String.valueOf(dayActiveUser));
            deviceStatistics.put("monthActiveUser", String.valueOf(monthActiveUser));

            // 再存入Redis缓存
            String current_date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String key = RedisKeyUtil.genHomepageRedisKey( "deviceStatistics", current_date);
            this.template.opsForHash().putAll(key, deviceStatistics);
            this.template.expire(key, 25, TimeUnit.HOURS);
        } else {
            logger.error(ip + " : " + deviceId + " : " + visitTime);
        }
    }
}
