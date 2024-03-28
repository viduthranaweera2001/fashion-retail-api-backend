package com.itpm.fashionretailapi.repository;

import com.itpm.fashionretailapi.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {

}
