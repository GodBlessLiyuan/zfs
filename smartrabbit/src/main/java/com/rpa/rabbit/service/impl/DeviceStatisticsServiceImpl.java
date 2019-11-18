package com.rpa.rabbit.service.impl;

import com.rpa.rabbit.mapper.DeviceStatisticsMapper;
import com.rpa.rabbit.pojo.DeviceStatisticsPO;
import com.rpa.rabbit.service.DeviceStatisticsService;
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

    @Autowired
    private DeviceStatisticsMapper deviceStatisticsMapper;
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
        this.template.opsForHash().putAll("deviceStatistics" + current_date, deviceStatistics);
        this.template.expire("deviceStatistics" + current_date, 25, TimeUnit.HOURS);
    }
}
