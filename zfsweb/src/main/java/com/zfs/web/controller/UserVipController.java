package com.zfs.web.controller;

import com.zfs.common.vo.ResultVO;
import com.zfs.web.service.IUserVipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("query")
    public ResultVO query(
           @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
           @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
           @RequestParam(value = "phone",required = false) String phone,
           @RequestParam(value = "isPay",required = false) Byte isPay) {
        Map<String, Object> reqData = new HashMap<>(2);
        reqData.put("phone", phone);
        reqData.put("isPay", isPay);

        return service.query(pageNum, pageSize, reqData);
    }

    @PostMapping("queryDetails")
    public ResultVO queryDetails(
         @RequestParam(value = "userId") int userId) {
        return service.queryDetails(userId);
    }
}
