package com.example.demo.kafka;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("test")  // Only active during tests
public class TestKafkaProducer implements KafkaProducerInterface {

    @Override
    public void sendMessage(String message) {
        System.out.println("📨 [TEST MODE] Kafka message: " + message);
        // Mock implementation - just log the message
    }
} 