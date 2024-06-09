package com.myworks.mywork.services.imp;

import com.myworks.mywork.services.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerServiceImp implements ProducerService {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.producer.topic}")
    private String topicName;

    @Override
    public void sendMessage(String message) {
            this.kafkaTemplate.send(topicName,message);
    }
}
