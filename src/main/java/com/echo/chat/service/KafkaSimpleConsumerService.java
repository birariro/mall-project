package com.echo.chat.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class KafkaSimpleConsumerService {

    @KafkaListener(topics = "message-topic", groupId = "group-id-oing")
    public void consume(String message) throws IOException{
        System.out.println("message = " + message);
    }
}
