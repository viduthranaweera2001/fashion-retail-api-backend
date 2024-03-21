package com.itpm.fashionretailapi.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;
    private Float totalPrice;
    private Float shippingPrice;
    private String address;
    private String city;
    private String phoneNumber;
    private String postalCode;

    @ManyToOne
    private User user;

    @OneToOne
    private Cart cart;
}
