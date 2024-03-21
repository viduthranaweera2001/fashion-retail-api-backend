package com.itpm.fashionretailapi.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cardHolderName;
    private String cardNo;
    private String expDate;
    private Integer cvvNo;

    @ManyToOne
    private User user;

    @OneToOne
    private Order order;

}
