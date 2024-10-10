package com.ecomm.protal.service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShippingAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    private Integer houseNumber;
    private String street;
    private String city;
    private String state;
    private Long zipcode;
    private String country;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private List<OrderItems> orders;
    @OneToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

}
