package com.main.server.service;

import com.main.server.domain.repository.EchoRepository;
import com.main.server.domain.Echo;
import com.main.server.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class EchoService {

    private final EchoRepository echoRepository;

    private final KafkaTemplate<String,String> kafkaTemplate;
    public Echo post(Member member, String context){

        Echo echo = new Echo(context);
        member.newEcho(echo);

        Echo save = echoRepository.save(echo);
        this.kafkaTemplate.send("message-topic", save.toString());
        return save;
    }

    @Transactional(readOnly = true)
    public Page<Echo> fetchAll(Pageable pageable){
        return echoRepository.findAll(pageable);
    }

    public Echo fetch(Long id){
        return echoRepository.findById(id)
                .orElseThrow(()-> new IllegalStateException("not exist echo id"));
    }
    public void update(){

    }
    public void delete(Long id){
        Echo fetch = fetch(id);
        fetch.inActive();
    }
}
