package com.main.server.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.main.server.domain.Order;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class OrderDto {

    private Long orderNo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime orderDate;
    private List<OrderLineDto> row;

    public OrderDto(Order order) {
        this.orderNo = order.getId();
        this.orderDate = order.getCreatedDate();
        System.out.println("this.orderDate = " + this.orderDate);
        this.row = order.getOrderLines().stream().map(orderLine -> {
            return new OrderLineDto(orderLine.getId(), orderLine.getProduct().getName(), orderLine.getProduct().getPrice(), orderLine.getCount(), orderLine.getOrderPrice());
        }).collect(Collectors.toList());
    }
}
