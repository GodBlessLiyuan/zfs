package com.zfs.server.controller;

import com.zfs.common.vo.ResultVO;
import com.zfs.server.dto.LoginDTO;
import com.zfs.server.service.ILoginService;
import com.zfs.server.service.ISmsService;
import com.zfs.server.utils.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
    private ILoginService loginService;
    @Autowired
    private ISmsService smsService;

    @PostMapping("sms")
    public ResultVO sms(@RequestBody LoginDTO dto) {
        if (!VerifyUtil.checkDeviceId(dto)) {
            return ResultVO.paramsError();
        }

        if (!VerifyUtil.checkPhone(dto.getPh())) {
            return new ResultVO(1026);
        }



        if (smsService.sendSMS(dto.getPh()) == 1) {
            return new ResultVO(1000);
        } else {
            return new ResultVO<>(2000);
        }
    }

    @PostMapping("register")
    public ResultVO register(@RequestBody LoginDTO dto, HttpServletRequest req) {
        if (!VerifyUtil.checkDeviceId(dto) || !VerifyUtil.checkPhone(dto.getPh())) {
            return ResultVO.paramsError();
        }

        return loginService.register(dto, req);
    }

    /**
     * 重新获取token接口
     */
    @PostMapping("regettoken")
    public ResultVO regettoken(@RequestBody LoginDTO dto, HttpServletRequest req) {
        if (!VerifyUtil.checkDeviceId(dto) || !VerifyUtil.checkPhone(dto.getPh())) {
            return ResultVO.paramsError();
        }
        return loginService.regettoken(dto, req);
    }
    @PostMapping("logout")
    public ResultVO logout(@RequestBody LoginDTO dto, HttpServletRequest req) {
        if (!VerifyUtil.checkDeviceId(dto)) {
            return ResultVO.paramsError();
        }
        return loginService.logout(dto, req);
    }
}
