package com.echo.chat.service;

import com.echo.chat.domain.Member;
import com.echo.chat.vo.Email;
import com.echo.chat.vo.LoginID;
import com.echo.chat.vo.NickName;
import com.echo.chat.config.auth.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public String login(String id, String pwd){
        Member member = memberService.fetchMember(id);

        if( ! passwordEncoder.matches(pwd,member.getLoginPWD())){
            throw new IllegalStateException("not match login password");
        }

        return getToken(id,pwd);
    }

    private String getToken(String id ,String pwd){
        //https://studyandwrite.tistory.com/499
        //https://velog.io/@suhongkim98/Spring-Security-JWT%EB%A1%9C-%EC%9D%B8%EC%A6%9D-%EC%9D%B8%EA%B0%80-%EA%B5%AC%ED%98%84%ED%95%98%EA%B8%B0
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(id, pwd);

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.createToken(authentication);

        return token;
    }
    public Member join(LoginID id, String pwd, NickName nickName, Email email){


        if(memberService.existLoginId(id.getValue()))
            throw new IllegalStateException("exist member login id");

        if(memberService.existNickName(nickName))
            throw new IllegalStateException("exist member nickName");

        String encodePwd = passwordEncoder.encode(pwd);

        return memberService.saveMember(id,encodePwd,nickName,email);
    }
}
