package com.zfs.server.service;

import com.zfs.common.vo.ResultVO;
import com.zfs.server.dto.DeviceDTO;

/**
 * @author: xiahui
 * @date: Created in 2019/10/15 16:05
 * @description: 设备接口
 * @version: 1.0
 */
public interface IDeviceService {
    /**
     * 查询设备
     *
     * @param dto 设备参数
     * @return
     */
    ResultVO queryDevice(DeviceDTO dto);
}
