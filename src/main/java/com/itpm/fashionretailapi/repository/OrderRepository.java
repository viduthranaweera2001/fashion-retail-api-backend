package com.itpm.fashionretailapi.repository;

import com.itpm.fashionretailapi.model.Order;
import com.itpm.fashionretailapi.model.User;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findOrdersByName(String name);
    List<Order> findOrdersByUser(User user);
}
