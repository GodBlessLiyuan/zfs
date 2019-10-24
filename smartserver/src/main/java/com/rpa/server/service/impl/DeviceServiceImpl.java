package com.rpa.server.service.impl;

import com.rpa.server.common.ResultVO;
import com.rpa.server.dto.DeviceDTO;
import com.rpa.server.mapper.DeviceImeiMapper;
import com.rpa.server.mapper.DeviceMapper;
import com.rpa.server.pojo.DeviceImeiPO;
import com.rpa.server.pojo.DevicePO;
import com.rpa.server.service.IDeviceService;
import com.rpa.server.utils.RedisCacheUtil;
import com.rpa.server.vo.DeviceVO;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: xiahui
 * @date: Created in 2019/10/15 16:05
 * @description: 设备接口实现
 * @version: 1.0
 */
@Service
public class DeviceServiceImpl implements IDeviceService {

    @Resource
    private DeviceMapper deviceMapper;
    @Resource
    private DeviceImeiMapper deviceImeiMapper;
    @Resource
    private RedisCacheUtil cache;

    @Override
    public ResultVO queryDevice(DeviceDTO dto) {
        List<String> imei = dto.getImei();
        if (imei == null || imei.size() == 0) {
            // 新增设备信息
            DevicePO devicePO = new DevicePO();
            this.updatePObyDTO(devicePO, dto);
            deviceMapper.insert(devicePO);
            return this.buildResultVO(devicePO.getDeviceId());
        }

        List<Long> deviceIds = deviceImeiMapper.queryDevIdsByImei(imei);
        if (deviceIds == null || deviceIds.size() == 0) {
            // 没有查询到相关设备信息
            // 新增设备信息
            DevicePO devicePO = new DevicePO();
            this.updatePObyDTO(devicePO, dto);
            deviceMapper.insert(devicePO);
            // 新增设备imei号
            List<DeviceImeiPO> devImeiPOs = new ArrayList<>();
            for (String im : imei) {
                DeviceImeiPO po = new DeviceImeiPO();
                po.setDeviceId(devicePO.getDeviceId());
                po.setImei(im);
                devImeiPOs.add(po);
            }
            deviceImeiMapper.batchInsert(devImeiPOs);
            return this.buildResultVO(devicePO.getDeviceId());
        }

        if (deviceIds.size() == 1) {
            // 查询出一条设备信息
            Long deviceId = deviceIds.get(0);
            DevicePO devicePO = this.updateDevicePO(deviceId, dto);
            deviceMapper.updateByPrimaryKey(devicePO);
            return this.buildResultVO(devicePO.getDeviceId());
        }

        return new ResultVO<>(2000);
    }

    /**
     * 生成待更新的设备PO
     *
     * @param deviceId 设备Id
     * @param dto
     * @return
     */
    private DevicePO updateDevicePO(Long deviceId, DeviceDTO dto) {
        DevicePO devicePO = deviceMapper.selectByPrimaryKey(deviceId);
        this.updatePObyDTO(devicePO, dto);
        return devicePO;
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

    /**
     * 跟新po值
     *
     * @param po
     * @param dto
     */
    private void updatePObyDTO(DevicePO po, DeviceDTO dto) {
        if (dto.getAndroidid() != null) {
            po.setAndroidid(dto.getAndroidid());
        }
        if (dto.getUtdid() != null) {
            po.setUtdid(dto.getUtdid());
        }
        if (dto.getOsv() != null) {
            po.setBuildversion(dto.getOsv());
        }
        if (dto.getOsre() != null) {
            po.setBuildrelease(dto.getOsre());
        }
        if (dto.getChannel() != null) {
            po.setSoftChannelId(cache.getSoftChannelId(dto.getChannel()));
        }
        if (dto.getFactory() != null) {
            po.setManufacturer(dto.getFactory());
        }
        if (dto.getModel() != null) {
            po.setAndroidmodel(dto.getModel());
        }
        if (dto.getSoftv() != null) {
            po.setVersioncode(dto.getSoftv());
        }
        if (dto.getUuid() != null) {
            po.setUuid(dto.getUuid());
        }
    }
}
