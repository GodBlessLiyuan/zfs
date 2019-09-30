package com.rpa.web.controller;

import com.rpa.web.dto.WhilteDeviceDTO;
import com.rpa.web.service.IWhilteDeviceService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/9/30 14:20
 * @description: 测试白名单
 * @version: 1.0
 */
@RestController
public class WhilteDeviceController {

    @Resource
    private IWhilteDeviceService service;

    @RequestMapping("/whiltedevice/query")
    public DTPageInfo<WhilteDeviceDTO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                             @RequestParam(value = "start", defaultValue = "1") int pageNum,
                                             @RequestParam(value = "length", defaultValue = "10") int pageSize,
                                             @RequestParam(value = "imei") String imei) {
        Map<String, Object> reqData = new HashMap<>(1);
        reqData.put("imei", imei);

        return service.query(draw, pageNum, pageSize, reqData);
    }

    @RequestMapping("/whiltedevice/insert")
    public int insert(@RequestParam(value = "packageName") String imei,
                      @RequestParam(value = "extra") String extra) {
        return service.insert(imei, extra);
    }

    @RequestMapping("/whiltedevice/delete")
    public int delete(@RequestParam(value = "deviceId") int deviceId) {
        return service.deleteByDeviceId(deviceId);
    }
}
