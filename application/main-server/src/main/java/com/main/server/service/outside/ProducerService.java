package com.main.server.service.outside;


import com.main.server.domain.Member;
import com.main.server.domain.Order;
import com.main.server.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProducerService {

    private final KafkaTemplate<String,String> kafkaTemplate;

    private final KafkaTemplate<String,Object> kafkaTemplate2;
    public void sendMail(Member member){
        this.kafkaTemplate.send("message-topic", member.getNickName());

    }
    public void orderMessage(String message){
        this.kafkaTemplate.send("new-order-topic", message);
    }

    public void objectProducerTest(Product product){
        System.out.println("호출  : "+product);
        this.kafkaTemplate2.send("object-topic", product);
    }

}
