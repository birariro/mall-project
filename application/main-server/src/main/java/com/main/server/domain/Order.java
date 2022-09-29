package com.main.server.domain;


import com.main.server.domain.base.BaseTimeEntity;
import com.main.server.domain.enums.OrderStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TB_ORDER")
public class Order extends BaseTimeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member")
    private Member member;


    @OneToMany(mappedBy = "order")
    private List<OrderLine> orderLines = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Order(Member member) {
        this.member = member;
        this.status = OrderStatus.ORDER;
    }

    public void appendOrderLine(OrderLine orderLine){
        this.orderLines.add(orderLine);
        orderLine.setOrder(this);
    }
}
