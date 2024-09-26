package com.ecomm.protal.service.repo;

import com.ecomm.protal.service.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(path = "product-category")
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Long> {


}
