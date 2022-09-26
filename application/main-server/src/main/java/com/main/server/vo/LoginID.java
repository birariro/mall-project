package com.main.server.vo;

import lombok.Getter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
public class LoginID implements Serializable {
    private static final long serialVersionUID = 1L;

    private String value;

    protected LoginID() {
    }

    public LoginID(String value) {
        this.value = value;
    }


}
