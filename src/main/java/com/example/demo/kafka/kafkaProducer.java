package com.example.demo.kafka;

//import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
//@Profile("!test")  // Don't load during tests
public class kafkaProducer implements KafkaProducerInterface {

    private KafkaTemplate<String, String> kafkaTemplate;

    public kafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        System.out.println("KafkaTemplate created");
    }

    @Override
    public void sendMessage(String message) {
        kafkaTemplate.send("demo_topic", message);
        System.out.println("Message sent to Kafka: " + message);
    }
}
