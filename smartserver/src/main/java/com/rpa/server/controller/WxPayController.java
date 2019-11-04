package com.rpa.server.controller;

import com.rpa.server.service.IWxPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("wxpaynotice")
    public String wxPayNotice(HttpServletRequest req) {

        return service.wxPayNotice(req);
    }
}
