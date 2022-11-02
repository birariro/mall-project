package com.main.server.controller;

import com.main.server.common.response.ResponseService;
import com.main.server.common.response.result.LinksResult;
import com.main.server.controller.dto.request.OrderRequest;
import com.main.server.controller.dto.response.OrderDto;
import com.main.server.domain.Member;
import com.main.server.domain.Order;
import com.main.server.domain.OrderLine;
import com.main.server.service.ProductService;
import com.main.server.service.member.MemberService;
import com.main.server.service.order.OrderService;
import com.main.server.service.order.dto.OrderProducts;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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

        List<OrderProducts> products = new ArrayList<OrderProducts>();
        for (OrderRequest orderRequest : orderRequests) {
            OrderProducts orderProducts = new OrderProducts(productService.fetch(orderRequest.getProductId()), orderRequest.getProductCount());
            products.add(orderProducts);
        }

        Order post = orderService.post(authMember, products);


        HashMap<String, Long> responseData = new HashMap<>();
        responseData.put("no", post.getId());

        List<LinksResult> links = new ArrayList<>();
        links.add(new LinksResult("list","/order"));
        links.add(new LinksResult("detail","/order/{no}"));

        return ResponseEntity.status(HttpStatus.CREATED).body(responseService.getResult(responseData,links));
    }

    @GetMapping
    public ResponseEntity fetchOrders(){

        Member authMember = memberService.getAuthMember();
        List<OrderDto> result = authMember.getOrders().stream().map(OrderDto::new).collect(Collectors.toList());

        List<LinksResult> links = new ArrayList<>();
        links.add(new LinksResult("detail","/order/{no}"));
        return ResponseEntity.status(HttpStatus.CREATED).body(responseService.getResult(result,links));
    }

    @GetMapping("/{id}")
    public ResponseEntity fetchOrder(@PathVariable Long id){

        Member authMember = memberService.getAuthMember();
        Order order = orderService.fetchMemberWithOrder(authMember, id);
        OrderDto orderDto = new OrderDto(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseService.getResult(orderDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity cancelOrder(@PathVariable Long id) throws IllegalAccessException {

        Member authMember = memberService.getAuthMember();
        orderService.orderCancel(authMember, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
