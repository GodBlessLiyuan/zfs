package com.rpa.kafka.controller;

import com.rpa.kafka.common.ResultVO;
import com.rpa.kafka.dto.KafkaDTO;
import com.rpa.kafka.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: xiahui
 * @date: Created in 2019/10/21 20:16
 * @description: Kafka producer
 * @version: 1.0
 */
@RequestMapping("v1.0")
@RestController
public class KafkaController {
    @Autowired
    private KafkaService service;

    @PostMapping("s_data")
    public ResultVO send(@RequestBody KafkaDTO dto, HttpServletRequest req) {
        return service.sendMsg(dto.getData(), req);
    }
}
