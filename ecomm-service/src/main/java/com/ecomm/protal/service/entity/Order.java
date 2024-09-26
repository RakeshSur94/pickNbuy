package com.ecomm.protal.service.entity;

import com.ecomm.protal.service.constants.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

/*@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer order_id;
    private Long order_tracking_num;
    private Integer total_quantity;
    private Double total_price;
    @Enumerated(EnumType.STRING)
    private OrderStatus order_status;
    private LocalDate date_created;
    private LocalDate last_updated;
    private Long razor_pay_payment_id;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id ")
    private List<OrderItems> ordersItems;
}*/
