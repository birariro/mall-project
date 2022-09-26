package com.main.server.vo;

import lombok.Getter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
public class NickName implements Serializable {
    private static final long serialVersionUID = 1L;
    private String value;

    public NickName(String value) {
        this.value = value;
    }

    protected NickName() {

    }
}
