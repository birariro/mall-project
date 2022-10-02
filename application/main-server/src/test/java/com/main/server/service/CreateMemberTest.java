package com.main.server.service;

import com.main.server.domain.Member;
import com.main.server.domain.repository.MemberRepository;
import com.main.server.service.member.LoginService;
import com.main.server.domain.vo.Email;
import com.main.server.domain.vo.LoginID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class CreateMemberTest {

    @Autowired
    private LoginService loginService;

    @Autowired
    private MemberRepository memberRepository;
    public String loginId = "loginTest";

    private String loginPwd = "loginPwd";
    private String nickName = "nickname";
    private String email = "email@naver.com";


    @Test
    @DisplayName("[성공] 회원가입")
    public void joinSuccess(){
        
        Member saveMember = loginService.join(new LoginID(loginId), loginPwd, new Email(email));
        Assertions.assertNotNull(saveMember);


        System.out.println("saveMember = " + saveMember);
        Assertions.assertEquals(loginId, saveMember.getLoginID());
        Member findMember = memberRepository.findById(saveMember.getId())
                                                .orElseGet(() -> Assertions.fail());

        Assertions.assertEquals(loginId, findMember.getLoginID());
    }

    @Test
    @DisplayName("[성공] 로그인")
    public void loginSuccess(){

        joinSuccess();

        String token= loginService.login(loginId, loginPwd);

        System.out.println("token = " + token);

        assertNotNull(token);

    }
}