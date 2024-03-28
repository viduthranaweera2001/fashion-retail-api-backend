package com.itpm.fashionretailapi.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    private Double price;

    @ManyToOne
    private User user;
    @ManyToOne
    private Product product;
}
