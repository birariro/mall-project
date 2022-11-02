package com.main.server.domain;


import com.main.server.domain.base.BaseTimeEntity;
import com.main.server.domain.value.Address;
import com.main.server.domain.vo.Email;
import com.main.server.domain.vo.LoginID;
import com.main.server.domain.vo.NickName;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;


@Entity
@Getter
@ToString(exclude = {"authorities"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TB_MEMBER")
public class Member extends BaseTimeEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "login_id")
    private String loginID;

    @Column(name = "login_pwd")
    private String loginPWD;

    @Embedded
    private NickName nickName;

    @Embedded
    private Email email;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "tb_member_authority",
            joinColumns = {@JoinColumn(name = "login_id", referencedColumnName = "login_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")}
    )
    private Set<Authority> authorities = new HashSet<>();


    public Member(LoginID loginID, String loginPWD, NickName nickName, Email email,  Address address) {
        this.loginID = loginID.getValue();
        this.loginPWD = loginPWD;
        this.nickName = nickName;
        this.email = email;
        this.address = address;

        Authority authority = Authority.userAuth();
        this.authorities.add(authority);
    }



    public String getEmail() {
        return email.getValue();
    }

    public String getLoginID() {
        return loginID;
    }

    public String getNickName(){
        return nickName.getValue();
    }



    public boolean isAdmin(){

        Optional<Authority> first = this.authorities.stream()
                .filter(Authority::isAdmin)
                .findFirst();

        return first.isPresent();
    }

    public void changeEmail(Email newEmail){
        this.email = newEmail;
    }

    public void changeNickName(NickName newNickName){
        this.nickName = newNickName;
    }

    public void changeAddress(Address address){
        this.address = address;
    }

}
