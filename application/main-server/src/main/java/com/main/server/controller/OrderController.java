package com.main.server.controller;

import com.main.server.common.response.ResponseService;
import com.main.server.controller.dto.request.OrderRequest;
import com.main.server.domain.Member;
import com.main.server.service.ProductService;
import com.main.server.service.member.MemberService;
import com.main.server.service.order.OrderService;
import com.main.server.service.order.dto.OrderProducts;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final MemberService memberService;
    private final OrderService orderService;
    private final ProductService productService;
    private final ResponseService responseService;
    @PostMapping
    public ResponseEntity createOrder(@RequestBody OrderRequest[] orderRequests){

        Member authMember = memberService.getAuthMember();

        List<OrderProducts> products = new ArrayList();
        for (OrderRequest orderRequest : orderRequests) {
            OrderProducts orderProducts = new OrderProducts(productService.fetch(orderRequest.getProductId()), orderRequest.getProductCount());
            products.add(orderProducts);
        }

        orderService.post(authMember,products);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseService.getSuccessResult());
    }
}
