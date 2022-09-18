package com.echo.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EchoChatServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EchoChatServerApplication.class, args);
    }

}
