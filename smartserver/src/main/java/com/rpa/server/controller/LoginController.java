package com.rpa.server.controller;

import com.rpa.server.service.ISmsService;
import com.rpa.server.utils.RedisCacheUtil;
import com.rpa.server.common.ResultVO;
import com.rpa.server.dto.LoginDTO;
import com.rpa.server.service.ILoginService;
import com.rpa.server.utils.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

/**
 * @author: xiahui
 * @date: Created in 2019/10/16 9:43
 * @description: 注册/登录 模块
 * @version: 1.0
 */
@RequestMapping("v1.0")
@RestController
public class LoginController {

    @Autowired
    private RedisCacheUtil cache;
    @Autowired
    private ILoginService loginService;
    @Autowired
    private ISmsService smsService;

    @PostMapping("sms")
    public ResultVO sms(@RequestBody LoginDTO dto) {
        if (!VerifyUtil.checkDeviceId(dto.getId(), dto.getVerify()) || !VerifyUtil.checkPhone(dto.getPh())) {
            return new ResultVO<>(2000);
        }

        // 6位验证码
        String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);
        cache.cacheVerifyCode(dto.getPh(), verifyCode);

        if(smsService.sendSMS(dto.getPh(),verifyCode) == 1){
            return new ResultVO(1000);
        }else {
            return new ResultVO<>(2000);
        }
    }

    @PostMapping("register")
    public ResultVO register(@RequestBody LoginDTO dto, HttpServletRequest req) {
        if (!VerifyUtil.checkDeviceId(dto.getId(), dto.getVerify()) || !VerifyUtil.checkPhone(dto.getPh())) {
            return new ResultVO(2000);
        }
        // 短信码过期
        if(!cache.checkSmsByCache(dto.getPh(), dto.getSms())){
            return new ResultVO(1013);
        }

        return loginService.register(dto, req);
    }
}
