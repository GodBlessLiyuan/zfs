package com.rpa.consumer.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @author: xiahui
 * @date: Created in 2019/10/21 20:22
 * @description: Kafka consumer
 * @version: 1.0
 */
@Service
public class ConsumerService {

    @KafkaListener(topics = {"smart"})
    public void consume(Object payload) {
        System.out.println(payload);
    }
}
