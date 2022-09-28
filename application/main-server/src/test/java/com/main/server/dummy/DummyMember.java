package com.main.server.dummy;

import com.main.server.domain.Member;
import com.main.server.service.member.LoginService;
import com.main.server.vo.Email;
import com.main.server.vo.LoginID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class DummyMember {

    @Autowired
    private LoginService loginService;
    public static String loginId = "loginTest";

    private String loginPwd = "loginPwd";
    private String nickName = "nickname";
    private String email = "email@naver.com";

    @BeforeEach
    public void createDummyMember(){
        Member saveMember = loginService.join(new LoginID(loginId), loginPwd, new Email(email));
        Assertions.assertNotNull(saveMember);
    }
}
