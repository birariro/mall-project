package com.main.server.domain;


import com.main.server.domain.base.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "TB_ORDER_LINE")
public class OrderLine extends BaseTimeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_line_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column
    private Long orderPrice;

    @Column
    private Long count;

    public OrderLine(Product product,  Long count) {
        this.product = product;
        this.count = count;
        this.orderPrice = calcOrderPrice(this.product, this.count);

        product.downStockQuantity(count);
    }

    private long calcOrderPrice(Product product,  Long count){
        return product.getPrice() * count;
    }

    public void setOrder(Order order){
        this.order = order;
    }

    public void cancel(){
        this.product.upStockQuantity(this.count);
    }
}
