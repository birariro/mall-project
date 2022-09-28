package com.order.server.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private RestTemplate restTemplate = new RestTemplate();

    @PostMapping
    public ResponseEntity newOrder(@RequestHeader(value = "Authorization") String token){

        if(token == null || token == "" || ! token.startsWith("Bearer"))  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("valid not token");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization",token);

        HttpEntity<String> entity = new HttpEntity<String>("", headers);

        ResponseEntity<Long> exchange = restTemplate.exchange("http://localhost:8081/auth", HttpMethod.GET, entity, Long.class);

        System.out.println("member id = " + exchange.getBody());

        return ResponseEntity.status(HttpStatus.CREATED).body(token);
    }
}
