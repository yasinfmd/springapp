package com.myworks.mywork.controller;

import com.myworks.mywork.services.ProducerService;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile("!prod")
@RequestMapping("/api/kafka")
public class KafkaController {

    private final ProducerService producerService;

    @Autowired
    public KafkaController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @GetMapping
    public String sendMessageToKafka(@RequestParam("msg") String msg) {
        System.out.println("Kafka log send starting...");
        for (int i = 0; i < 10; i++) {
            this.producerService.sendMessage(msg + "-" + i);
        }
        System.out.println("Kafka log sent...");
        return "Message was sent";
    }
}
