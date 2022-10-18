package com.main.server.service.order;

import com.main.server.domain.Member;
import com.main.server.domain.Order;
import com.main.server.domain.OrderLine;
import com.main.server.domain.repository.OrderLineRepository;
import com.main.server.domain.repository.OrderRepository;
import com.main.server.service.ProductService;
import com.main.server.service.member.MemberService;
import com.main.server.service.order.dto.OrderProducts;
import com.main.server.service.outside.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderLineRepository orderLineRepository;
    private final ProducerService producerService;
    public Order post(Member member, List<OrderProducts> products){

        Order order = new Order(member);
        products.stream()
                .map(orderProducts -> new OrderLine(orderProducts.getProduct(), orderProducts.getProductCount()))
                .forEach(orderLine -> {
                    order.appendOrderLine(orderLine);
                    orderLineRepository.save(orderLine);
                    producerService.orderMessage(String.valueOf(orderLine.getProduct().getId()));
                    producerService.objectProducerTest(orderLine.getProduct());
                });


        Order save = orderRepository.save(order);
        producerService.sendMail(member);
        return save;
    }

    public Order fetch(Long id){
        Order order = orderRepository.findById(id).orElseThrow(() -> new IllegalStateException("not exist order"));
        return order;
    }

    public Order fetchMemberWithOrder(Member member, Long id){
        return orderRepository.findByIdAndMember(id,member).orElseThrow(() -> new IllegalStateException("not exist member with order"));
    }
}
