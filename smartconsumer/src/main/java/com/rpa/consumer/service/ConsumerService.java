package com.rpa.consumer.service;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * @author: xiahui
 * @date: Created in 2019/10/21 20:22
 * @description: TODO
 * @version: 1.0
 */
@EnableBinding(Sink.class)
public class ConsumerService {

    @StreamListener(Sink.INPUT)
    public void consume(Object payload) {
        System.out.println(payload);
    }
}
