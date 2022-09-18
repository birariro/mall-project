package com.echo.chat.controller.dto.response;


import com.echo.chat.domain.Echo;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class EchoDto {

    private long no;
    private String author;
    private String title;
    private String context;


    public EchoDto(Echo echo) {
        this.no = echo.getId();
        this.author = echo.getMember().getNickName();
        this.title = echo.getTitle();
        this.context = echo.getContext();
    }
}
