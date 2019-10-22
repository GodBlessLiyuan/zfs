package com.rpa.producer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;

/**
 * @author: xiahui
 * @date: Created in 2019/10/21 20:14
 * @description: TODO
 * @version: 1.0
 */
@EnableBinding(Source.class)
public class ProducerService {

    @Autowired
    private Source source;

    public void sendMsg(String msg) {
        source.output().send(MessageBuilder.withPayload(msg).build());
    }
}
