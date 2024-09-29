package com.ecomm.protal.service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;


@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String title;
    private BigDecimal unitPrice;
    private String imageUrl;
    private Boolean active;
    private Integer units_stock;
    private Date date_created;
    private Date last_updated;
    @ManyToOne
    @JoinColumn(name = "category_id",nullable = false)
    private ProductCategory categories;


}
