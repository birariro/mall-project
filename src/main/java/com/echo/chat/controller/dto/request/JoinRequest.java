package com.echo.chat.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Getter
@NoArgsConstructor
public class JoinRequest {
    @NotBlank(message = "파라미터가 비워져있다.")
    private String id;
    @NotBlank
    private String pwd;
    @NotBlank
    private String nickName;
    @NotBlank
    private String email;
}
