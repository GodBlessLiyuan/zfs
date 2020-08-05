package com.zfs.kafka;

import org.springframework.beans.factory.annotation.Value;
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

    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class, args);
    }

    @Value("${kafka.jaas.conf.path}")
    public void setKafkaConfPath(String kafkaConfPath) {
        System.setProperty("java.security.auth.login.config", kafkaConfPath);
    }
}
