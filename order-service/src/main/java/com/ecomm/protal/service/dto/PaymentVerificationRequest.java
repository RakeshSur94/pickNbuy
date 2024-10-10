package com.ecomm.protal.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentVerificationRequest {
    private String orderId;
    private String paymentId;
    private String signature;
}
