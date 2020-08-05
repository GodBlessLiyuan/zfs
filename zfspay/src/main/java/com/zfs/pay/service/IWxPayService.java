package com.zfs.pay.service;


import com.zfs.common.vo.ResultVO;
import com.zfs.pay.dto.WxPayDTO;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: xiahui
 * @date: Created in 2019/11/4 9:54
 * @description: 微信支付
 * @version: 1.0
 */
public interface IWxPayService {

    /**
     * 微信支付下单
     *
     * @param dto
     * @param req
     * @return
     */
    ResultVO wxPayOrder(WxPayDTO dto, HttpServletRequest req);

    /**
     * 微信支付结果通知
     *
     * @param req
     * @return
     */
    String wxPayNotify(HttpServletRequest req);
}
