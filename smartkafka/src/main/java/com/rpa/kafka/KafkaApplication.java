package com.rpa.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author: xiahui
 * @date: Created in 2019/10/21 20:12
 * @description: Kafka producer
 * @version: 1.0
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class KafkaApplication {

    //初始化系统属性
    static {
        System.setProperty("java.security.auth.login.config", "classpath:kafka_client_jaas.conf");
    }

    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class, args);
    }
}
