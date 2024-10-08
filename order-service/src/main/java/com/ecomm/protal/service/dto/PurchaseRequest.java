package com.ecomm.protal.service.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseRequest {
    private String orderStatus;
    private String customerDetails;
}
