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
    @DisplayName("[성공] 주문 성공 테스트")
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

    @Test
    @DisplayName("[실패] 주문 재고 이탈 테스트")
    public void createFailStockOrder(){

        List<Product> productAll = productRepository.findAll();
        Assertions.assertTrue(productAll.size() > 0);

        List<Member> memberAll = memberRepository.findAll();
        Assertions.assertTrue(memberAll.size() > 0);

        Product product = productAll.get(productAll.size() - 1);
        Member member = memberAll.get(memberAll.size() - 1);

        System.out.println("member = " + member);
        System.out.println("product = " + product);

        //재고 보다 큰 주문
        List<OrderProducts> list = List.of(new OrderProducts(product, product.getStockQuantity() + 10L));

        Assertions.assertThrows(IllegalStateException.class, () -> {
            orderService.post(member, list);
        });

    }

    @Test
    @DisplayName("[성공] 주문 취소 테스트")
    public void orderCancel(){

        List<Product> productAll = productRepository.findAll();
        Assertions.assertTrue(productAll.size() > 0);

        List<Member> memberAll = memberRepository.findAll();
        Assertions.assertTrue(memberAll.size() > 0);

        Product product = productAll.get(productAll.size() - 1);
        Member member = memberAll.get(memberAll.size() - 1);



        Long oldStock = product.getStockQuantity();
        List<OrderProducts> list = List.of(new OrderProducts(product, oldStock));

        Order order = orderService.post(member, list);

        Assertions.assertEquals(0, product.getStockQuantity());

        order.cancel();
        Assertions.assertEquals(oldStock, product.getStockQuantity());

    }
}
