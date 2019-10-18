package com.rpa.server.service;

/**
 * @author: velve
 * @date: Created in 2019/10/18 10:23
 * @description: 发送短信模块
 * @version: 1.0
 */
public interface ISmsService {
    int sendSMS(String phone,String verifyCode);
}
