package com.harini.account_service.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "bank-events", groupId = "bank-group")
    public void listen(String msg) {

        System.out.println("Received Event: " + msg);
    }
}