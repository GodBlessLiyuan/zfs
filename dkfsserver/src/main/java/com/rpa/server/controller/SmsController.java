package com.rpa.server.controller;

import com.rpa.common.dto.TokenDTO;
import com.rpa.common.vo.ResultVO;
import com.rpa.server.dto.SmsDTO;
import com.rpa.server.service.ISmsService;
import com.rpa.server.utils.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: xiahui
 * @date: Created in 2020/1/17 15:25
 * @description: 验证码
 * @version: 1.0
 */
@RestController
@RequestMapping("v1.0")
public class SmsController {
    @Autowired
    private ISmsService service;

    @PostMapping("sendsms")
    public ResultVO sendSms(@RequestBody TokenDTO dto, HttpServletRequest req) {
        if (VerifyUtil.checkToken(dto, req)) {
            return new ResultVO(2000);
        }

        return service.sendSMS(dto.getUd());
    }

    @PostMapping("validatesms")
    public ResultVO validateSMS(@RequestBody SmsDTO dto, HttpServletRequest req) {
        if (VerifyUtil.checkToken(dto, req)) {
            return new ResultVO(2000);
        }

        return service.validateSMS(dto);
    }
}
