package com.rpa.producer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author: xiahui
 * @date: Created in 2019/10/21 20:14
 * @description: TODO
 * @version: 1.0
 */
@Service
public class ProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMsg(String msg) {
        this.kafkaTemplate.send("test", msg);
    }
}
