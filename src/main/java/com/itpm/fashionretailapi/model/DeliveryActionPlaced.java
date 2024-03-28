package com.itpm.fashionretailapi.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "delivery_actions_placed")
public class DeliveryActionPlaced {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private String phoneNo;
    private String customerEmail;
    private String customerName;
    private String deliveryPersonName;


    @ManyToOne
    @JoinColumn(name = "delivery_person_id")
    private DeliveryPerson deliveryPerson;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
