package com.itpm.fashionretailapi.model;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="payment_cards")
public class PaymentCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cardHolderName;
    private String cardNo;
    private String expDate;
    private Integer cvvNo;

    @ManyToOne
    private User user;
}
