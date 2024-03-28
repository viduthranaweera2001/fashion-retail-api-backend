package com.itpm.fashionretailapi.repository;

import com.itpm.fashionretailapi.model.Delivery;
import com.itpm.fashionretailapi.model.DeliveryActionPlaced;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<DeliveryActionPlaced,Long> {
}
