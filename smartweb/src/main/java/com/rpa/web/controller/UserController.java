package com.rpa.web.controller;

import com.rpa.web.dto.UserDTO;
import com.rpa.web.service.IUserService;
import com.rpa.web.utils.DTPageInfo;
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

    @RequestMapping("query")
    public DTPageInfo<UserDTO> list(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                    @RequestParam(value = "start", defaultValue = "1") int pageNum,
                                    @RequestParam(value = "length", defaultValue = "10") int pageSize,
                                    @RequestParam(value = "phone") String phone) {
        Map<String, Object> reqData = new HashMap<>(1);
        reqData.put("phone", phone);

        return service.query(draw, pageNum, pageSize, reqData);
    }
}
