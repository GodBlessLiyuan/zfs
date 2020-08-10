package com.zfs.web.controller;

import com.zfs.common.vo.ResultVO;
import com.zfs.web.service.IUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/9/24 15:05
 * @description: 用户信息
 * @version: 1.0
 */
@RequestMapping("userinfo")
@RestController
public class UserController {
    @Resource
    private IUserService service;

    @PostMapping("query")
    public ResultVO query(
           @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
           @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
           @RequestParam(value = "phone",required = false) String phone) {
        Map<String, Object> reqData = new HashMap<>(1);
        reqData.put("phone", phone);

        return service.query(pageNum, pageSize, reqData);
    }
}
