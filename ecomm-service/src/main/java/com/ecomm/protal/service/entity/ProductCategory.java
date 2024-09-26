package com.ecomm.protal.service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "product-category")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String categoryName;
    @OneToMany(mappedBy = "categories",cascade = CascadeType.ALL)
    private Set<Product> products;
}
