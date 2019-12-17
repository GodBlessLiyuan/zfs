package com.rpa.voice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: xiahui
 * @date: Created in 2019/12/2 11:26
 * @description: 语音盒子
 * @version: 1.0
 */
@SpringBootApplication
@MapperScan("com.rpa.common.mapper")
public class VoiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(VoiceApplication.class, args);
    }
}
