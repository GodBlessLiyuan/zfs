package com.rpa.pay.controller;

import com.rpa.pay.common.ResultVO;
import com.rpa.pay.dto.WxPayDTO;
import com.rpa.pay.service.IWxPayService;
import com.rpa.pay.utils.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: xiahui
 * @date: Created in 2019/11/4 9:52
 * @description: 微信支付
 * @version: 1.0
 */
@RequestMapping("v1.0")
@RestController
public class WxPayController {

    @Autowired
    private IWxPayService service;

    @PostMapping("wxpayorder")
    public ResultVO wxPayOrder(@RequestBody WxPayDTO dto, HttpServletRequest req) {
        if (!VerifyUtil.checkToken(dto, req)) {
            return new ResultVO(2000);
        }

        return service.wxPayOrder(dto, req);
    }

    @PostMapping("wxpaynotify")
    public String wxPayNotify(HttpServletRequest req) {

        return service.wxPayNotify(req);
    }
}
