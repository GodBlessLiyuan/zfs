package com.rpa.consumer.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author: xiahui
 * @date: Created in 2019/11/5 10:53
 * @description: Rabbitmq 消费服务
 * @version: 1.0
 */
@Service
public class RabbitmqService {
    /**
     * 微信支付确认通知
     * @param msg
     */
    @RabbitListener(queues = "wx-pay-notice")
    public void wxPayNotice(String msg) {
        System.out.println(msg);
    }
}
