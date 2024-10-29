package com.ecomm.protal.service.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseRequest {
    private Long orderTrackingNumber;
    private String customerDetails;
    private String orderId;
}
