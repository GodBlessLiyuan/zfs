package com.zfs.server.controller;

import com.zfs.common.vo.ResultVO;
import com.zfs.server.dto.DeviceDTO;
import com.zfs.server.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: xiahui
 * @date: Created in 2019/10/14 8:46
 * @description: 设备模块
 * @version: 1.0
 */
@RequestMapping("v1.0")
@RestController
public class DeviceController {

    @Autowired
    private IDeviceService service;

    @PostMapping("device")
    public ResultVO device(@RequestBody DeviceDTO dto) {
        return service.queryDevice(dto);
    }
}
