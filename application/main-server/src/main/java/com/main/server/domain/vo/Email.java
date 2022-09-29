package com.main.server.domain.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Email  {

    private String email;
    public Email(String value) {
        this.email = value;
    }

    public String getValue(){
        return this.email;
    }
}
