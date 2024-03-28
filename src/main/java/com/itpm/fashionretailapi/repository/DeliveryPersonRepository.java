package com.itpm.fashionretailapi.repository;

import com.itpm.fashionretailapi.model.DeliveryPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DeliveryPersonRepository extends JpaRepository<DeliveryPerson,Long> {
//    @Query("SELECT dp FROM DeliveryPerson dp WHERE dp.name = :name")
//    Optional<DeliveryPerson> findByName(String name);

    DeliveryPerson findByName(String name);
}
