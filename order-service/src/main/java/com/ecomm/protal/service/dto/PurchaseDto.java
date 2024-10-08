package com.ecomm.protal.service.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseDto {

    private CustomerDto customerDto;
    private List<OrderItemDto> ordersItemsDto = new ArrayList<>();
    private OrderDto orderDto;
    private ShippingAddressDto addressDto;
}
