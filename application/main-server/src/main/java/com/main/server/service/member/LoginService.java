package com.main.server.service.member;

import com.main.server.config.auth.TokenProvider;
import com.main.server.domain.Member;
import com.main.server.domain.value.Address;
import com.main.server.utils.NickNameUtils;
import com.main.server.domain.vo.Email;
import com.main.server.domain.vo.LoginID;
import com.main.server.domain.vo.NickName;
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
    public Member join(LoginID id, String pwd, Email email, Address address){


        if(memberService.existLoginId(id.getValue()))
            throw new IllegalStateException("exist member login id");

        NickName nickName = new NickName(NickNameUtils.getInstance().getRandomNickName());
        if(memberService.existNickName(nickName))
            throw new IllegalStateException("exist member nickName");

        String encodePwd = passwordEncoder.encode(pwd);

        return memberService.saveMember(id,encodePwd,nickName,email,address);
    }
}
