package com.rpa.pay.controller;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.rpa.pay.common.ResultVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.alipay.api.AlipayConstants.CHARSET;

/**
 * @author: dangyi
 * @date: Created in 17:34 2019/11/6
 * @version: 1.0.0
 * @description:
 */
@RequestMapping("v1.0")
@RestController
public class AlipayController {

    @PostMapping("alipay")
    public ResultVO alipay() {


        // 实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
                APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA2");
        // 实例化具体API对应的request类
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        // 传入业务参数
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody("我是测试数据");
        model.setSubject("App支付测试Java");
        model.setOutTradeNo("0001");
        model.setTimeoutExpress("30m");
        model.setTotalAmount("0.01");
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        request.setNotifyUrl("商户外网可以访问的异步地址");
    }
}
