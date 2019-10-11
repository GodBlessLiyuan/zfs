package com.rpa.web.controller;

import com.rpa.web.dto.DeviceDTO;
import com.rpa.web.service.IUnregisteredService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/9/29 11:32
 * @description: 未注册用户信息
 * @version: 1.0
 */
@RequestMapping("unregistered")
@RestController
public class UnregisteredController {

    @Resource
    private IUnregisteredService service;

    @RequestMapping("query")
    public DTPageInfo<DeviceDTO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                       @RequestParam(value = "start", defaultValue = "1") int pageNum,
                                       @RequestParam(value = "length", defaultValue = "10") int pageSize,
                                       @RequestParam(value = "channelId") int channelId) {
        Map<String, Object> reqData = new HashMap<>(1);
        reqData.put("channelId", channelId);

        return service.query(draw, pageNum, pageSize, reqData);
    }
}
