package com.example.demo.kafka;
import org.junit.jupiter.api.Test;

public class KafkaConsumerTest {

    @Test
    public void testListen() {
        kafkaConsumer consumer = new kafkaConsumer();
        consumer.listen("Hello, World!");
    }

    @Test
    public void testListenWithNullMessage() {
        kafkaConsumer consumer = new kafkaConsumer();
        consumer.listen(null);
    }

    @Test
    public void testListenWithEmptyMessage() {
        kafkaConsumer consumer = new kafkaConsumer();
        consumer.listen("");
    }
}