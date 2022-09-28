package com.main.server.service;


import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProducerService {

    private final KafkaTemplate<String,String> kafkaTemplate;
    public void post(String context){

        this.kafkaTemplate.send("message-topic", context);

    }

}
