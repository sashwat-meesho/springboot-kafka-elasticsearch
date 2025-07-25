package com.example.demo.kafka;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class KafkaProducerTest {
    
    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @InjectMocks
    private kafkaProducer producer;

    @Test
    public void testSendMessage() {
        String message = "Hello, World!";   
        producer.sendMessage(message);
        verify(kafkaTemplate, times(1)).send("demo_topic", message);
    }

    @Test
    public void testSendMessageWithNullMessage() {
        producer.sendMessage(null);
        verify(kafkaTemplate, times(1)).send("demo_topic", null);
    }

    @Test
    public void testSendMessageWithEmptyMessage() {
        producer.sendMessage("");
        verify(kafkaTemplate, times(1)).send("demo_topic", "");
    }
}