package com.ecomm.protal.service.utils;

import com.ecomm.protal.service.dto.CustomerDto;
import com.ecomm.protal.service.dto.OrderDto;
import com.ecomm.protal.service.dto.OrderItemDto;
import com.ecomm.protal.service.dto.ShippingAddressDto;
import com.ecomm.protal.service.entity.CustomerEntity;
import com.ecomm.protal.service.entity.OrderItems;
import com.ecomm.protal.service.entity.OrdersEntity;
import com.ecomm.protal.service.entity.ShippingAddress;

public class EcommerceServiceMapping {

    // Method to convert ShippingAddressDto to ShippingAddress entity
    public static ShippingAddress convertToShippingAddressEntity(ShippingAddressDto addressDto) {
        ShippingAddress address = new ShippingAddress();
        address.setStreet(addressDto.getStreet());
        address.setCity(addressDto.getCity());
        address.setState(addressDto.getState());
        address.setCountry(addressDto.getCountry());
        address.setZipcode(addressDto.getZipcode());
        address.setHouseNumber(addressDto.getHouseNumber());
        // Set other properties if needed
        return address;
    }
    // Method to convert CustomerDto to CustomerEntity
    public static CustomerEntity convertToCustomerEntity(CustomerDto customerDto) {
        CustomerEntity customer = new CustomerEntity();
        customer.setCustomerName(customerDto.getCustomerName());
        customer.setEmail(customerDto.getEmail());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setPassword(customerDto.getPassword());
        return customer;
    }
    public static OrdersEntity convertToOrderEntity(OrderDto orderDto) {
        OrdersEntity order = new OrdersEntity();
        order.setOrderTrackingNum(orderDto.getOrderTrackingNum());
        order.setTotalQuantity(orderDto.getTotalQuantity());
        order.setTotalPrice(orderDto.getTotalPrice());
        order.setOrderStatus(orderDto.getOrderStatus());
        order.setDateCreated(orderDto.getDateCreated());
        order.setLastUpdated(orderDto.getLastUpdated());
        // Set other properties if needed
        return order;
    }
    public static OrderDto convertToOrderDto(OrdersEntity order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderTrackingNum(order.getOrderTrackingNum());
        orderDto.setTotalQuantity(order.getTotalQuantity());
        orderDto.setTotalPrice(order.getTotalPrice());
        orderDto.setDateCreated(order.getDateCreated());
        orderDto.setLastUpdated(order.getLastUpdated());

        // Set other properties if needed
        return orderDto;
    }
    public static OrderItems convertToOrderItem(OrderItemDto orderItemDto) {
        return OrderItems.builder()
                .imageUrl(orderItemDto.getImageUrl())
                .unitPrice(orderItemDto.getUnitPrice())
                .quantity(orderItemDto.getQuantity())
                .productId(orderItemDto.getProductId())
                .build();

    }
}
