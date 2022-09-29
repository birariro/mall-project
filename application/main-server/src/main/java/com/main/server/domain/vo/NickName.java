package com.main.server.domain.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NickName{
    private String nickName;

    public NickName(String value) {
        this.nickName = value;
    }

    public String getValue(){
        return this.nickName;
    }
}
