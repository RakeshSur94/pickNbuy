package com.ecomm.protal.service.repo;

import com.ecomm.protal.service.entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItems,Long> {
}
