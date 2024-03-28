package com.itpm.fashionretailapi.repository;

import com.itpm.fashionretailapi.model.Cart;
import com.itpm.fashionretailapi.model.Product;
import com.itpm.fashionretailapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {

}
