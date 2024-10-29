package com.ecomm.protal.service.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDto {
    private Long orderItemId;
    private String imageUrl;
    private Double unitPrice;
    private Integer quantity;
    private Integer productId;
}
