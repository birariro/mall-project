package com.main.server.service;

import com.main.server.domain.Member;
import com.main.server.domain.Order;
import com.main.server.domain.Product;
import com.main.server.domain.repository.MemberRepository;
import com.main.server.domain.repository.OrderRepository;
import com.main.server.domain.repository.ProductRepository;
import com.main.server.dummy.DummyProduct;
import com.main.server.service.order.OrderService;
import com.main.server.service.order.dto.OrderProducts;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@Rollback(value = false)
public class OrderTest extends DummyProduct {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("주문 성공 테스트")
    public void createOrder(){

        List<Product> productAll = productRepository.findAll();
        Assertions.assertTrue(productAll.size() > 0);

        List<Member> memberAll = memberRepository.findAll();
        Assertions.assertTrue(memberAll.size() > 0);

        Product product = productAll.get(productAll.size() - 1);
        Member member = memberAll.get(memberAll.size() - 1);

        System.out.println("member = " + member);
        System.out.println("product = " + product);

        List<OrderProducts> list = List.of(new OrderProducts(product, 3L));

        Order order = orderService.post(member, list);

        Order findOrder = orderRepository.findById(order.getId()).get();

        Assertions.assertEquals(order,findOrder);

        Assertions.assertEquals(3L, findOrder.getOrderLines().get(0).getCount());
    }
}
