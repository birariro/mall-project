package com.echo.chat.dummy;

import com.echo.chat.domain.Member;
import com.echo.chat.vo.Email;
import com.echo.chat.vo.LoginID;
import com.echo.chat.vo.NickName;
import com.echo.chat.service.LoginService;
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
        Member saveMember = loginService.join(new LoginID(loginId), loginPwd, new NickName(nickName), new Email(email));
        Assertions.assertNotNull(saveMember);
    }
}
