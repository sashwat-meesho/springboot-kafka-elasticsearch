package com.example.demo.Scheduler;

import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.example.demo.kafka.kafkaProducer;

@Component
@Profile("!test")  // Don't run scheduler during tests
public class SampleScheduler {

    private final kafkaProducer producer;
    public SampleScheduler(kafkaProducer producer) {
        this.producer = producer;
    }

    @Scheduled(fixedRate = 5000) // every 5 seconds
    public void sendKafkaMessage() {
        producer.sendMessage("📦 Scheduled message at " + System.currentTimeMillis());
    }
}