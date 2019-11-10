package com.rpa.consumer.component;

import com.rpa.consumer.service.IPayService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: xiahui
 * @date: Created in 2019/11/5 10:53
 * @description: Rabbitmq 消费服务
 * @version: 1.0
 */
@Component
public class RabbitmqComponent {

    @Autowired
    private IPayService service;

    /**
     * 微信支付确认通知
     *
     * @param orderNumber 订单号
     */
    @RabbitListener(queues = "pay-notify")
    public void wxPayNotify(String orderNumber) {

        service.payNotify(orderNumber);
    }
}
