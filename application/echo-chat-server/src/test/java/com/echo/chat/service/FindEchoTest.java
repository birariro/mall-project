package com.echo.chat.service;

import com.echo.chat.domain.Echo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback
public class FindEchoTest {

    @Autowired
    private EchoService echoService;
    @Test
    @DisplayName("article 전체 조회")
    public void fetchAllArticle(){
        PageRequest of = PageRequest.of(0, 10);

        Page<Echo> articles = echoService.fetchAll(of);

        System.out.println("articles.getTotalElements() = " + articles.getTotalElements());
        Assertions.assertTrue(articles.getTotalElements() > 0);
    }
}
