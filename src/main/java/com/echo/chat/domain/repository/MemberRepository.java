package com.echo.chat.domain.repository;

import com.echo.chat.domain.Member;
import com.echo.chat.vo.NickName;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @EntityGraph(attributePaths = "authorities")
    Optional<Member> findOneWithAuthoritiesByLoginID(String loginID);


    boolean existsByLoginID(String loginId);
    boolean existsByNickName(NickName nickName);

    Optional<Member> findByLoginID(String loginId);
}
