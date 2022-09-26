package com.main.server.controller.dto.response;


import com.main.server.domain.Echo;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class EchoDto {

    private long no;
    private String author;
    private String context;


    public EchoDto(Echo echo) {
        this.no = echo.getId();
        this.author = echo.getMember().getNickName();
        this.context = echo.getContext();
    }
}
