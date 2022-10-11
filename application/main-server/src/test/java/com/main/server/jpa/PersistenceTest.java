package com.main.server.jpa;


import com.main.server.domain.Member;
import com.main.server.domain.repository.MemberRepository;
import com.main.server.domain.vo.Email;
import com.main.server.domain.vo.LoginID;
import com.main.server.domain.vo.NickName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class PersistenceTest {

    @Autowired
    MemberRepository memberRepository;


    Member gMember = new Member(new LoginID("gId"),"gpwd",new NickName("gNickName"),new Email("gEmail"));


    @Test
    public void signup(){
        Member save = memberRepository.save(gMember);
        Assertions.assertEquals(gMember,save);

    }

    @Test
    public void findMember(){
        Member member = memberRepository.findById(gMember.getId()).get();
        Assertions.assertEquals(gMember,member);
    }
}
