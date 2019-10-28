package com.rpa.front;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author: xiahui
 * @date: Created in 2019/10/25 18:43
 * @description: 前端
 * @version: 1.0
 */
@EnableCaching
@SpringBootApplication
public class FrontApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrontApplication.class, args);
    }
}
