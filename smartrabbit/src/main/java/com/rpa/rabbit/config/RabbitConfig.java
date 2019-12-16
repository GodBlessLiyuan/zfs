package com.rpa.rabbit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;

/**
 * @author: xiahui
 * @date: Created in 2019/11/4 19:07
 * @description: RabbitMQ 配置
 * @version: 1.0
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue smartServerQueue() {
        return new Queue("pay-notify");
    }

    @Bean
    public Queue register() {
        return new Queue("register");
    }

    /**
     * @author: dangyi
     * @date: Created in 2019/11/14
     * @description: 用户设备统计的消息队列
     * @return
     */
    @Bean
    public Queue queueForDeviceStatistics() {
        return new Queue("device_statistics");
    }
}
