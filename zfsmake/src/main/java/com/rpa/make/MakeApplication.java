package com.rpa.make;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: xiahui
 * @date: Created in 2020/1/11 17:05
 * @description: 制作微服务
 * @version: 1.0
 */
@MapperScan("com.rpa.common.mapper")
@SpringBootApplication
public class MakeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MakeApplication.class, args);
    }
}
