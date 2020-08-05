package com.zfs.rabbit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author: xiahui
 * @date: Created in 2019/11/14 10:28
 * @description: Rabbit MQ
 * @version: 1.0
 */
@SpringBootApplication
@EnableScheduling
public class RabbitApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitApplication.class, args);
    }
}
