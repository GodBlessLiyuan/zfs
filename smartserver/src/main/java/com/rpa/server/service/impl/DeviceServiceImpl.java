package com.rpa.server.service.impl;

import com.rpa.server.mapper.DeviceMapper;
import com.rpa.server.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: xiahui
 * @date: Created in 2019/10/15 16:05
 * @description: 设备接口实现
 * @version: 1.0
 */
@Service
public class DeviceServiceImpl implements IDeviceService {

    @Autowired
    private DeviceMapper deviceMapper;
}
