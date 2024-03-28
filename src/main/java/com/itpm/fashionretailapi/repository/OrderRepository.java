package com.itpm.fashionretailapi.repository;

import com.itpm.fashionretailapi.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {
//    @Query("SELECT o FROM Order o WHERE o.user.name = :customerName")
//    Optional<Order> findByCustomerName(String customerName);

//    @Query("SELECT o FROM Order o WHERE o.user.id = :customerId")
//    Optional<Order> findByCustomerId(@Param("customerId") Long customerId);
}
