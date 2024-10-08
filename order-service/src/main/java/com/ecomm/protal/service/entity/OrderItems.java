package com.ecomm.protal.service.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "orders_items")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;
    private String imageUrl;
    private Double unitPrice;
    private Integer quantity;
    private Integer productId;

}