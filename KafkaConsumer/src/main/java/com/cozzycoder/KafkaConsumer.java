package com.cozzycoder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KafkaConsumer {
    public static void main(String[] args) {
        SpringApplication.run(KafkaConsumer.class, args);
    }
}