package com.order.server.controller;


import com.order.server.service.AuthService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private RestTemplate restTemplate = new RestTemplate();
    private final AuthService authService;

    @PostMapping
    public ResponseEntity newOrder(@RequestHeader(value = "Authorization") String token) throws IllegalAccessException {

        Long memberId = authService.getAuthMemberId(token);

        return ResponseEntity.status(HttpStatus.CREATED).body(memberId);
    }

}
