package com.rpa.producer.service;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/10/21 20:14
 * @description: Kafka producer
 * @version: 1.0
 */
@Service
public class ProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMsg(Object data, HttpServletRequest req) {
        Map<String, Object> msg = new HashMap<>(1);
        msg.put("ip", req.getRemoteAddr());
        msg.put("data", data);
        this.kafkaTemplate.send("smart", JSON.toJSONString(msg));
    }
}
