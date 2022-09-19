package com.echo.chat.domain;

import com.echo.chat.domain.base.BaseStateEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TB_ECHO")
public class Echo extends BaseStateEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "context")
    private String context;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member")
    private Member member;

    public Echo(String context) {
        this.context = context;
    }

    public void setMember(Member member){
        this.member = member;
    }
}
