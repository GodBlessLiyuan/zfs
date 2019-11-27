package com.rpa.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: xiahui
 * @date: Created in 2019/11/27 9:10
 * @description: 基础公共服务库，不作为微服务启动
 * @version: 1.0
 */
@SpringBootApplication
public class CommonLibApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonLibApplication.class, args);
    }
}
