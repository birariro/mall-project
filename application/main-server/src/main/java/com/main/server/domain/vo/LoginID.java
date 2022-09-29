package com.main.server.domain.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginID {

    private String loginID;


    public LoginID(String value) {
        this.loginID = value;
    }
    public String getValue(){
        return this.loginID;
    }

}
