package com.rpa.pay.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: xiahui
 * @date: Created in 2019/11/7 16:36
 * @description: 微信支付下单VO
 * @version: 1.0
 */
@Data
public class WxPayVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 微信支付账号的应用id
     */
    private String appid;
    /**
     * 随机字符串
     */
    private String noncestr;
    /**
     * 订单号
     */
    private String order_number;
    /**
     * 该字段固定使用"Sign=WXPay"
     */
    private String pkg;
    /**
     * 商户号
     */
    private String partnerid;
    /**
     * 预支付编号
     */
    private String prepayid;
    /**
     * 商品的价格，单位为分
     */
    private Long price;
    /**
     * 签名
     */
    private String sign;
    /**
     * 时间戳
     */
    private Long timestamp;
}
