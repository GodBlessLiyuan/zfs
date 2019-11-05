package com.rpa.consumer.config;

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
public class RabbitmqConfig {

    @Bean
    public Queue smartServerQueue() {
        return new Queue("wx-pay-notice");
    }
}
