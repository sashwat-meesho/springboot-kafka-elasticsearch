package com.example.demo.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class kafkaProducer {

    private KafkaTemplate<String, String> kafkaTemplate;

    public kafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        System.out.println("KafkaTemplate created");
    }

    public void sendMessage(String message) {
        kafkaTemplate.send("demo_topic", message);
        System.out.println("Message sent to Kafka: " + message);
    }
}
