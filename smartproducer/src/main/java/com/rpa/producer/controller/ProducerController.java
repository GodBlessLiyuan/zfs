package com.rpa.producer.controller;

import com.rpa.producer.dto.ProducerDTO;
import com.rpa.producer.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: xiahui
 * @date: Created in 2019/10/21 20:16
 * @description: TODO
 * @version: 1.0
 */
@RequestMapping("v1.0")
@RestController
public class ProducerController {
    @Autowired
    private ProducerService service;

    @PostMapping("s_data")
    public void send(@RequestBody ProducerDTO dto) {
        service.sendMsg(dto.getData());
    }
}
