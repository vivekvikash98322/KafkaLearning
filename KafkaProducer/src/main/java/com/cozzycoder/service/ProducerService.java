package com.cozzycoder.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ProducerService {

    private Executor executor;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    AtomicInteger atomicInteger = new AtomicInteger(1);

    @PostConstruct
    public void init(){
        executor = Executors.newFixedThreadPool(3);
    }


    @Scheduled(fixedDelay = 5000)
    public void sendMessage(){
        System.out.println("Sending message....................." + atomicInteger.get());
        CompletableFuture<SendResult<String, String>> stringCompletableFuture = this.kafkaTemplate.send("order", "order_created", "This is my " + atomicInteger.getAndAdd(1) + " Order");

        stringCompletableFuture.whenComplete((stringStringSendResult, throwable) -> {
            if(throwable == null){
                System.out.println(stringStringSendResult.getProducerRecord().value());
            }
        });
    }
}
