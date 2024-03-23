package com.itpm.fashionretailapi.repository;

import com.itpm.fashionretailapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
