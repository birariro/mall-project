package com.echo.chat.service;

import com.echo.chat.domain.Echo;
import com.echo.chat.domain.repository.EchoRepository;
import com.echo.chat.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class EchoService {

    private final EchoRepository echoRepository;
    public void post(Member member, String context){

        Echo echo = new Echo(context);
        member.newEcho(echo);

        echoRepository.save(echo);
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
