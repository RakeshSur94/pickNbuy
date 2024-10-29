package com.ecomm.protal.user.service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customer-details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    private String customerName;
    private String email;
    private String password;
    private String phoneNumber;
}
