package com.itpm.fashionretailapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "supplers")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String nic;
    private String phoneNo;
    private String email;

    @OneToMany(mappedBy = "supplier")
    private List<Product> productList;
}
