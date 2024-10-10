package com.ecomm.protal.service.repo;

import com.ecomm.protal.service.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<OrdersEntity,Long> {
    @Query(value = "SELECT * FROM orders WHERE razor_pay_payment_id = :razorPayPaymentId", nativeQuery = true)
    public OrdersEntity findByRazorPayPaymentId(@Param("razorPayPaymentId") String razorPayPaymentId);
}
