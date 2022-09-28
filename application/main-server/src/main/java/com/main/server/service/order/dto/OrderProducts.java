package com.main.server.service.order.dto;

import com.main.server.domain.Product;
import lombok.Getter;

@Getter
public class OrderProducts {
    private Product product;
    private Long productCount;

    public OrderProducts(Product product, Long productCount) {
        this.product = product;
        this.productCount = productCount;
    }
}
