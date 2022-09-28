package com.main.server.dummy;

import com.main.server.domain.Echo;
import com.main.server.domain.Member;
import com.main.server.domain.repository.EchoRepository;
import com.main.server.domain.repository.ProductRepository;
import com.main.server.service.ProducerService;
import com.main.server.service.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@SpringBootTest
@Transactional
public class DummyProduct extends DummyMember{

    @Autowired
    private ProducerService producerService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    EntityManager em;

    @BeforeEach
    public void createDummyArticle(){

        String context = "dummy context";

        Member member = memberService.fetchMember(loginId);
        Assertions.assertNotNull(member);

//        producerService.post(member,context);
//
//        em.flush();
//        em.clear();
//        List<Echo> all = echoRepository.findAll();
//        Assertions.assertTrue(all.size()>0);
    }
}
