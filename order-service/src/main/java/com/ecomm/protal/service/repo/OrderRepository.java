package com.ecomm.protal.service.repo;

import com.ecomm.protal.service.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrdersEntity,Long> {
public OrdersEntity findByRazorPayPaymentId(String razorPayPaymentId);
}
