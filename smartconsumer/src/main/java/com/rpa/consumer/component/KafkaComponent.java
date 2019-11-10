package com.rpa.consumer.component;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @author: xiahui
 * @date: Created in 2019/10/21 20:22
 * @description: Kafka 消费服务
 * @version: 1.0
 */
@Service
public class KafkaComponent {

    @KafkaListener(topics = {"smart"})
    public void kafkaConsume(Object payload) {
        System.out.println(payload);
    }
}


