package com.rpa.server.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: xiahui
 * @date: Created in 2019/11/4 9:54
 * @description: 微信支付
 * @version: 1.0
 */
public interface IWxPayService {
    /**
     * 微信支付结果通知
     *
     * @param req
     * @return
     */
    String wxPayNotice(HttpServletRequest req);
}
