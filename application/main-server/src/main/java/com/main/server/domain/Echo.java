package com.main.server.domain;

import com.main.server.domain.base.BaseStateEntity;
import com.main.server.vo.Location;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TB_ECHO")
@ToString
public class Echo extends BaseStateEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "context")
    private String context;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "location"))
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member")
    private Member member;

    public Echo(String context) {
        this.context = context;
    }

    public void setMember(Member member){
        this.member = member;
        this.location = new Location(member.getLocation());
    }
}
