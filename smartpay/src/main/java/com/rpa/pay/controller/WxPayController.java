package com.rpa.pay.controller;

import com.rpa.pay.common.ResultVO;
import com.rpa.pay.dto.WxPayDTO;
import com.rpa.pay.service.IWxPayService;
import com.rpa.pay.utils.VerifyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    private final static Logger logger = LoggerFactory.getLogger(WxPayController.class);

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
        logger.info("WxPay-Rabbit-req: ", req);
        return service.wxPayNotify(req);
    }
}
