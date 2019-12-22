package com.rpa.server.service.impl;

import com.rpa.common.mapper.DeviceImeiMapper;
import com.rpa.common.mapper.DeviceMapper;
import com.rpa.common.pojo.DeviceImeiPO;
import com.rpa.common.pojo.DevicePO;
import com.rpa.common.utils.LogUtil;
import com.rpa.common.vo.ResultVO;
import com.rpa.server.dto.DeviceDTO;
import com.rpa.server.service.IDeviceService;
import com.rpa.server.utils.RedisCacheUtil;
import com.rpa.server.vo.DeviceVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.*;

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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO queryDevice(DeviceDTO dto) {
        List<String> imeis = dto.getImei();
        if (null == imeis || imeis.size() == 0) {
            // 新增设备信息
            DevicePO po = this.buildDevicePO(new DevicePO(), dto);
            po.setCreateTime(new Date());
            int result = deviceMapper.insert(po);
            if (result == 0) {
                LogUtil.log(logger, "queryDevice", "插入失败", po);
            }

            return this.buildResultVO(po.getDeviceId());
        }

        // 过滤imei号长度<13
        for(String imei : imeis) {
            if(imei.length() < 13) {
                imeis.remove(imei);
            }
        }


        List<Long> deviceIds = deviceImeiMapper.queryDevIdsByImei(imeis);
        if (null == deviceIds || deviceIds.size() == 0) {
            logger.warn("DeviceIds size: " + (null == deviceIds ? null : deviceIds.size()));
            // 没有查询到相关设备信息
            // 新增设备信息
            DevicePO po = this.buildDevicePO(new DevicePO(), dto);
            po.setCreateTime(new Date());
            int result1 = deviceMapper.insert(po);
            if (result1 == 0) {
                LogUtil.log(logger, "insert", "新增设备信息失败", po);
            }

            // 新增设备imei号，需去重
            List<DeviceImeiPO> imeiPOs = new ArrayList<>();

            for (String imei : new HashSet<>(imeis)) {
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
        vo.setVerify(DigestUtils.md5DigestAsHex(deviceId.toString().getBytes()));
        return new ResultVO<>(1000, vo);
    }
}
