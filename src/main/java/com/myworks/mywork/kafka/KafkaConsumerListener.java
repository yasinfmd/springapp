package com.myworks.mywork.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerListener {
   // @KafkaListener(topics = "test-topic", groupId = "consumer-group")
    public void listen(String message) {
        System.out.printf("Received Messasge: [%s] %n", message);
    }
}
