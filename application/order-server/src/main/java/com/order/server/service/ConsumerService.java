package com.order.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ConsumerService {

    @Autowired
    private RedisTemplate<String, Integer> redisTemplate;

    @KafkaListener(topics = "new-order-topic")
    public void consume(String message) throws IOException {
        System.out.println("팔린 제품 = " + message);

        productOrderRedis(message);
    }

    public void productOrderRedis(String productName){

        ValueOperations<String, Integer> valueOperations = redisTemplate.opsForValue();

        Integer findRedis = valueOperations.get(productName);
        //System.out.println("findRedis = " + findRedis);
        int oldCount = findRedis == null ? 0 : findRedis;
        valueOperations.set(productName, ++oldCount);

        //Integer findRedis2 = valueOperations.get(productName);
        //System.out.println("findRedis2 = " + findRedis2);

    }


}
