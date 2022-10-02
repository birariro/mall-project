package com.main.server.domain.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NickName implements Serializable{
    private static final long serialVersionUID = 1L;
    @Column(nullable = false, unique = true)
    private String nickName;

    public NickName(String value) {
        this.nickName = value;
    }

    public String getValue(){
        return this.nickName;
    }
}
