package com.main.server.service.member;

import com.main.server.domain.repository.MemberRepository;
import com.main.server.vo.Email;
import com.main.server.vo.LoginID;
import com.main.server.vo.NickName;
import com.main.server.common.SecurityUtil;
import com.main.server.domain.Member;
import lombok.RequiredArgsConstructor;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    @Cacheable(value = "Member", key = "#loginId")
    public Member fetchMember(String loginId){
        System.out.println("loginId = " + loginId);
        return memberRepository
                .findByLoginID(loginId)
                .orElseThrow(() -> new IllegalStateException("not exist member"));
    }

    public Page<Member> fetchAllMember(Pageable pageable){

        return memberRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public boolean existLoginId(String id){
        return memberRepository.existsByLoginID(id);
    }
    @Transactional(readOnly = true)
    public boolean existNickName(NickName nickName){
        return memberRepository.existsByNickName(nickName);
    }

    public Member saveMember(LoginID id, String pwd, NickName nickName, Email email ){
        Member member = new Member(id, pwd, nickName, email);
        return memberRepository.save(member);
    }

    public void update(){

    }

    public void delete(){
        Member authMember = getAuthMember();
        authMember.inActive();
    }

    public Member getAuthMember(){
        return SecurityUtil.getCurrentUsername().flatMap(memberRepository::findOneWithAuthoritiesByLoginID)
                .orElseThrow(()->new IllegalStateException("not exist member"));
    }
}
