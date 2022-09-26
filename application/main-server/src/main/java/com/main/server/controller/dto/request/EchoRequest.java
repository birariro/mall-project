package com.main.server.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class EchoRequest {
    @NotBlank
    private String context;

}
