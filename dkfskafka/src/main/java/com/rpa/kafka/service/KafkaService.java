package com.rpa.kafka.service;

import com.alibaba.fastjson.JSON;
import com.rpa.common.utils.RequestUtil;
import com.rpa.kafka.common.ResultVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class KafkaService {
    @Value("${kafka.default.topic}")
    private String topic;

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    public ResultVO sendMsg(Object data, HttpServletRequest req) {
        Map<String, Object> msg = new HashMap<>(1);
        msg.put("ip", RequestUtil.getIpAddr(req));
        msg.put("data", data);
        this.kafkaTemplate.send(topic, JSON.toJSONString(msg));

        return new ResultVO(1000);
    }
}