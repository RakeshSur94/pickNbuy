package com.ecomm.protal.service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private Long orderTrackingNum;
    private Integer totalQuantity;
    private Double totalPrice;
    private String orderStatus;
    private Date dateCreated;
    private Date lastUpdated;
    private String razorPayPaymentId;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private List<OrderItems> ordersItems;


}
