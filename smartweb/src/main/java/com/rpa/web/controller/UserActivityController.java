package com.rpa.web.controller;

import com.rpa.web.dto.UserActivityDTO;
import com.rpa.web.service.IUserActivityService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/10/9 11:04
 * @description: 活动赠送记录
 * @version: 1.0
 */
@RestController
public class UserActivityController {

    @Autowired
    private IUserActivityService service;

    @RequestMapping("/useractivity/query")
    public DTPageInfo<UserActivityDTO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                             @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                             @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                             @RequestParam(value = "phone", required = false) String phone) {
        Map<String, Object> reqData = new HashMap<>(1);
        reqData.put("phone", phone);

        return service.query(draw, pageNum, pageSize, reqData);
    }
}
