package com.zfs.pay.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: xiahui
 * @date: Created in 2019/11/7 11:23
 * @description: 微信支付配置
 * @version: 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "wxpayconfig")
public class WxPayConfig {

    /**
     * 应用ID
     */
    private String appid;
    /**
     * 商户号
     */
    private String mch_id;
    /**
     * 设备号
     */
    private String device_info;
    /**
     * 商户密匙
     */
    private String key;
    /**
     * 下单地址
     */
    private String order_url;
    /**
     * 通知地址
     */
    private String notify_url;
    /**
     *
     */
    private String wx_package;
}
