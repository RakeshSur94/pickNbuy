package com.ecomm.protal.user.service.repo;

import com.ecomm.protal.user.service.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity,Long> {
    public Optional<CustomerEntity> findByEmail(String email);
    public CustomerEntity findByEmailAndPassword(String email,String password);
}
