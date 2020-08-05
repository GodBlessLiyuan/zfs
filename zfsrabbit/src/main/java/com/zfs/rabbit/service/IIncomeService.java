package com.zfs.rabbit.service;

/**
 * @author: xiahui
 * @date: Created in 2019/11/8 18:22
 * @description: 支付服务接口
 * @version: 1.0
 */
public interface IIncomeService {
    /**
     * 支付通知
     *
     * @param orderNumber
     */
    void payNotify(String orderNumber);

    /**
     * 用户注册
     *
     * @param phone
     */
    void register(String phone);
}
