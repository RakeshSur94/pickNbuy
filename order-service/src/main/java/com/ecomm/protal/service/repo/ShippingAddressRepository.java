package com.ecomm.protal.service.repo;

import com.ecomm.protal.service.entity.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingAddressRepository extends JpaRepository<ShippingAddress,Long> {
}
