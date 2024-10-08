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
public class ShippingAddressDto {
    private Long addressId;
    private Integer houseNumber;
    private String street;
    private String city;
    private String state;
    private Long zipcode;
    private String country;
    private List<OrderDto> orders;
}
