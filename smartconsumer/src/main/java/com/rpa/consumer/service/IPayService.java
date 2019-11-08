package com.rpa.consumer.service;

/**
 * @author: xiahui
 * @date: Created in 2019/11/8 18:22
 * @description: 支付服务接口
 * @version: 1.0
 */
public interface IPayService {
    /**
     * 支付通知
     *
     * @param orderNumber
     */
    void payNotify(String orderNumber);
}
