package com.itpm.fashionretailapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String role;
    private String name;
    private String email;
    private String password;
    private String avatar;

    @OneToMany(mappedBy = "user")
    private List<Payment> paymentList;

    @OneToMany(mappedBy = "user")
    private List<Cart> cartList;

    @OneToMany(mappedBy = "user")
    private List<Delivery> deliveryList;

    @OneToMany(mappedBy = "user")
    private List<Review_Rating> reviewRatingList;

    // Add a method to retrieve the profile image path or filename
    public String getProfileImage() {
        return avatar; // Assuming avatar represents the profile image path or filename
    }
}
