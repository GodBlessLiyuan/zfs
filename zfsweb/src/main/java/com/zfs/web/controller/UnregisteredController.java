package com.zfs.web.controller;

import com.zfs.common.vo.ResultVO;
import com.zfs.web.vo.DeviceVO;
import com.zfs.web.service.IUnregisteredService;
import com.zfs.web.utils.DTPageInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
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
    public ResultVO query(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                          @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                          @RequestParam(value = "channelId", required = false) Integer channelId) {
        Map<String, Object> reqData = new HashMap<>(1);
        reqData.put("channelId", channelId);

        return service.query(pageNum, pageSize, reqData);
    }

    @RequestMapping("export")
    protected void export(HttpServletResponse response){
        service.export(response);
    }
}
