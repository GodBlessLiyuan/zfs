package com.rpa.consumer.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @author: xiahui
 * @date: Created in 2019/10/21 20:22
 * @description: Kafka 消费服务
 * @version: 1.0
 */
@Service
public class KafkaService {

    @KafkaListener(topics = {"smart"})
    public void kafkaConsume(Object payload) {
        System.out.println(payload);
    }
}


