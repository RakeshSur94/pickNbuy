package com.ecomm.protal.service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/*@Entity
@Table(name = "shipping_address")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ShippingAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer address_id;
    private Integer house_num;
    private String  street;
    private String city;
    private String state;
    private Long zipcode;
    private String country;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private List<Order> orders;
}*/
