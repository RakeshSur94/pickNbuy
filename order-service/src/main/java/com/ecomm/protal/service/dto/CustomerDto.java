package com.ecomm.protal.service.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
    private List<OrderDto> orders;
}
