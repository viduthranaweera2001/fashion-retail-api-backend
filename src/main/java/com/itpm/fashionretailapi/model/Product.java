package com.itpm.fashionretailapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Float price;
    private Integer stock;
    private String description;
    private String color;
    private String brand;
    private String imgUrl;

    @ManyToOne
    private ProductCategory productCategory;

    @ManyToOne
    private Supplier supplier;

    @OneToMany(mappedBy = "product")
    private List<Review_Rating> reviewRatingList;

    @OneToMany(mappedBy = "product")
    private List<Cart> cartList;

}
