package com.example.demo.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class kafkaConsumer {

    @KafkaListener(topics = "demo_topic", groupId = "demo_group")
    public void listen(String message) {
        System.out.println("Message received from Kafka: " + message);
    }
}
