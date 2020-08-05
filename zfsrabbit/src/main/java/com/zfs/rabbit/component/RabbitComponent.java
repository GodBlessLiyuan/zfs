package com.zfs.rabbit.component;

import com.zfs.rabbit.service.*;
import com.zfs.rabbit.service.DeviceStatisticsService;
import com.zfs.rabbit.service.IIncomeService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/11/5 10:53
 * @description: Rabbitmq 消费服务
 * @version: 1.0
 */
@Component
public class RabbitComponent {

    @Autowired
    private IIncomeService service;

    @Autowired
    private DeviceStatisticsService deviceStatisticsService;

    /**
     * 微信支付确认通知
     *
     * @param orderNumber 订单号
     */
    @RabbitListener(queues = "pay-notify")
    public void wxPayNotify(String orderNumber) {
        service.payNotify(orderNumber);
    }


    /**
     * 微信支付确认通知
     *
     * @param phone 注册手机号
     */
    @RabbitListener(queues = "register")
    public void register(String phone) {
        service.register(phone);
    }


    /**
     * @author: dangyi
     * @date: Created in 2019/11/14
     * @description: 发送用户设备消息
     * @param deviceInfo 用户设备访问信息
     */
    @RabbitListener(queues = "device_statistics")
    public void deviceStatic(Map<String, Object> deviceInfo) {
        deviceStatisticsService.deviceStatic(deviceInfo);
    }
}
