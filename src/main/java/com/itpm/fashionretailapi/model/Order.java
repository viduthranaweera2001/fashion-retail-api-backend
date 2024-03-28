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

    private Float shippingPrice;
    private String address;
    private String city;
    private String phoneNumber;
    private String postalCode;
    private Float TotalPrice;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne
    private Cart cart;
}
