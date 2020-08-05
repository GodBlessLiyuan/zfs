package com.rpa.web.controller;

import com.rpa.web.vo.UserVipVO;
import com.rpa.web.vo.UserVipDetailsVO;
import com.rpa.web.service.IUserVipService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/10/9 14:27
 * @description: 用户会员数据
 * @version: 1.0
 */
@RequestMapping("uservip")
@RestController
public class UserVipController {

    @Autowired
    private IUserVipService service;

    @RequestMapping("query")
    public DTPageInfo<UserVipVO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                       @RequestParam(value = "start", defaultValue = "1") int pageNum,
                                       @RequestParam(value = "length", defaultValue = "10") int pageSize,
                                       @RequestParam(value = "phone") String phone,
                                       @RequestParam(value = "isPay") byte isPay) {
        Map<String, Object> reqData = new HashMap<>(2);
        reqData.put("phone", phone);
        reqData.put("isPay", isPay);

        return service.query(draw, pageNum, pageSize, reqData);
    }

    @RequestMapping("queryDetails")
    public DTPageInfo<UserVipDetailsVO> queryDetails(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                                     @RequestParam(value = "start", defaultValue = "1") int pageNum,
                                                     @RequestParam(value = "length", defaultValue = "10") int pageSize,
                                                     @RequestParam(value = "userId") int userId) {
        return service.queryDetails(draw, pageNum, pageSize, userId);
    }
}
