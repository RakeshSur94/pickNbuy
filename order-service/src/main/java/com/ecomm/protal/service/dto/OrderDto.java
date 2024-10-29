package com.ecomm.protal.service.dto;

import com.ecomm.protal.service.entity.CustomerEntity;
import com.ecomm.protal.service.entity.ShippingAddress;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {
    private Long orderId;
    private Long orderTrackingNum;
    private Integer totalQuantity;
    private Double totalPrice;
    private String orderStatus;
    private Date dateCreated;
    private Date lastUpdated;
    private String razorPayPaymentId;
    private List<OrderItemDto> ordersItems;

}
