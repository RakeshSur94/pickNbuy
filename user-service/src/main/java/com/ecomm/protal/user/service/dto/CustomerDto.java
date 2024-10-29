package com.ecomm.protal.user.service.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {
    private Long customerId;
    private String customerName;
    private String email;
    private String password;
    private String phoneNumber;

}
