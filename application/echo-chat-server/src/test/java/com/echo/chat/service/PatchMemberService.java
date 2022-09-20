package com.echo.chat.service;

import com.echo.chat.domain.Member;
import com.echo.chat.dummy.DummyMember;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback
class PatchMemberService extends DummyMember {

    @Autowired
    private MemberPatchService memberPatchService;

    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName("member email 변경")
    public void memberChangeEmail(){
        Member fetchMember = memberService.fetchMember(DummyMember.loginId);

        Assertions.assertNotNull(fetchMember);

        String op = "replace";
        String path = "email";
        String value ="newemail2@newemail.com";

        memberPatchService.patch(fetchMember,op,path,value);

        Member member = memberService.fetchMember(DummyMember.loginId);

        Assertions.assertEquals(value,member.getEmail());
    }

    @Test
    @DisplayName("member nickName 변경")
    public void memberChangeNickName(){
        Member fetchMember = memberService.fetchMember(DummyMember.loginId);

        Assertions.assertNotNull(fetchMember);

        String op = "replace";
        String path = "nickName";
        String value ="newNickName";

        memberPatchService.patch(fetchMember,op,path,value);

        Member member = memberService.fetchMember(DummyMember.loginId);


        Assertions.assertEquals(value,member.getNickName());
    }
}