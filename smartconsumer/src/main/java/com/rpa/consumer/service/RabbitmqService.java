package com.rpa.consumer.service;

import com.rpa.consumer.mapper.OrderMapper;
import com.rpa.consumer.pojo.OrderPO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: xiahui
 * @date: Created in 2019/11/5 10:53
 * @description: Rabbitmq 消费服务
 * @version: 1.0
 */
@Service
public class RabbitmqService {

    @Resource
    private OrderMapper orderMapper;

    /**
     * 微信支付确认通知
     *
     * @param orderNumber 订单号
     */
    @RabbitListener(queues = "wx-pay-notice")
    public void wxPayNotice(String orderNumber) {
//        OrderPO orderPO = orderMapper.queryByOrderNumber(orderNumber);

    }
}
