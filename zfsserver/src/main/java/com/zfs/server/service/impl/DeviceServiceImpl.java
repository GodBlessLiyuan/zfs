package com.zfs.server.service.impl;

import com.zfs.common.mapper.DeviceImeiMapper;
import com.zfs.common.mapper.DeviceMapper;
import com.zfs.common.mapper.UserGiftsMapper;
import com.zfs.common.pojo.DeviceImeiPO;
import com.zfs.common.pojo.DevicePO;
import com.zfs.common.pojo.UserGiftsPO;
import com.zfs.common.utils.LogUtil;
import com.zfs.common.utils.RedisKeyUtil;
import com.zfs.common.vo.ResultVO;
import com.zfs.server.dto.DeviceDTO;
import com.zfs.server.service.IDeviceService;
import com.zfs.server.utils.RedisCacheUtil;
import com.zfs.server.vo.DeviceVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author: xiahui
 * @date: Created in 2019/10/15 16:05
 * @description: 设备接口实现
 * @version: 1.0
 */
@EnableTransactionManagement
@Service
public class DeviceServiceImpl implements IDeviceService {
    private static final Logger logger = LoggerFactory.getLogger(DeviceServiceImpl.class);

    @Resource
    private DeviceMapper deviceMapper;
    @Resource
    private DeviceImeiMapper deviceImeiMapper;
    @Resource
    private RedisCacheUtil cache;

    @Value("${verify.config.salt}")
    private String salt;
    @Resource
    private UserGiftsMapper userGiftsMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO queryDevice(DeviceDTO dto) {
        List<String> imeis = dto.getImei();
        if (null == imeis || imeis.size() == 0) {
            DevicePO devicePO = deviceMapper.queryByUtdID(dto.getUtdid());
            if (devicePO != null) {
                return this.buildResultVO(devicePO.getDeviceId());
            }
            // 新增设备信息
            DevicePO po = this.buildDevicePO(new DevicePO(), dto);
            po.setCreateTime(new Date());
            int result = deviceMapper.insert(po);
            if (result == 0) {
                LogUtil.log(logger, "queryDevice", "插入失败", po);
            }

            return this.buildResultVO(po.getDeviceId());
        }
        List<String> resultImei = new ArrayList<>(imeis.size());
        // 过滤imei号长度<13
        for (String imei : imeis) {
            if (imei.length() < 13) {
            } else {
                resultImei.add(imei);
            }
        }


        List<Long> deviceIds = deviceImeiMapper.queryDevIdsByImei(resultImei);
        if (null == deviceIds || deviceIds.size() == 0) {
            logger.warn("DeviceIds size: " + (null == deviceIds ? null : deviceIds.size()));
            // 没有查询到相关设备信息
            DevicePO devicePO = deviceMapper.queryByUtdID(dto.getUtdid());
            if (devicePO != null) {
                return this.buildResultVO(devicePO.getDeviceId());
            }
            // 新增设备信息
            DevicePO po = this.buildDevicePO(new DevicePO(), dto);
            po.setCreateTime(new Date());
            int result1 = deviceMapper.insert(po);
            if (result1 == 0) {
                LogUtil.log(logger, "insert", "新增设备信息失败", po);
            }

            // 新增设备imei号，需去重
            List<DeviceImeiPO> imeiPOs = new ArrayList<>();

            for (String imei : new HashSet<>(resultImei)) {
                DeviceImeiPO imeiPO = new DeviceImeiPO();
                imeiPO.setDeviceId(po.getDeviceId());
                imeiPO.setImei(imei);
                imeiPOs.add(imeiPO);
            }
            int result2 = deviceImeiMapper.batchInsert(imeiPOs);
            if (result2 == 0) {
                LogUtil.log(logger, "insert", "插入imeiPOs失败", imeiPOs);
            }

            return buildResultVO(po.getDeviceId());
        }

        if (deviceIds.size() == 1) {
            logger.warn("DeviceId: " + deviceIds);
            // 查询出一条设备信息
            DevicePO po = deviceMapper.selectByPrimaryKey(deviceIds.get(0));
            this.buildDevicePO(po, dto);
            //解决了更新的bug： Duplicate entry 'uuid' for key 'uuid'
            po.setUuid(null);
            po.setUpdateTime(new Date());
            int result3 = deviceMapper.updateByPrimaryKeySelective(po);
            if (result3 == 0) {
                LogUtil.log(logger, "insert", "插入失败", po);
            }

            return this.buildResultVO(po.getDeviceId());
        }

        logger.warn("DeviceIds size > 1");
        return new ResultVO<>(2000);
    }

    /**
     * 构建 DevicePO
     *
     * @param po
     * @param dto
     */
    private DevicePO buildDevicePO(DevicePO po, DeviceDTO dto) {
        po.setAndroidid(dto.getAndroidid());
        po.setUtdid(dto.getUtdid());
        po.setBuildversion(dto.getOsv());
        po.setBuildrelease(dto.getOsre());
        po.setSoftChannelId(cache.getSoftChannelId(dto.getChannel()));
        po.setChanName(dto.getChannel());
        po.setManufacturer(dto.getFactory());
        po.setAndroidmodel(dto.getModel());
        po.setVersioncode(dto.getSoftv());
        po.setVersionname(dto.getSoftn());
        po.setUuid(dto.getUuid());
        return po;
    }

    /**
     * 根据设备Id，生产返回结果
     *
     * @param deviceId 设备Id
     * @return
     */
    private ResultVO buildResultVO(Long deviceId) {
        DeviceVO vo = new DeviceVO();
        vo.setId(deviceId);
        vo.setVerify(DigestUtils.md5DigestAsHex((salt + deviceId).getBytes()));
        String deviceDay = RedisKeyUtil.genRedisKey("device", "days");
        String cacheDays = cache.getCacheByKey(deviceDay);
        if (cacheDays == null) {
            List<UserGiftsPO> userGiftsPOS = userGiftsMapper.queryOpenGift();
            Integer days;
            if (userGiftsPOS == null || userGiftsPOS.size() == 0) {
                days = 0;
            } else {
                days = userGiftsPOS.get(0).getDays();
                if (days == null) {
                    days = 0;
                }
            }
            cache.setCacheWithDate(deviceDay, days, 1, TimeUnit.DAYS);
            vo.setDays(days);
        } else {
            vo.setDays(Integer.parseInt(cacheDays));
        }

        return new ResultVO<>(1000, vo);
    }
}
