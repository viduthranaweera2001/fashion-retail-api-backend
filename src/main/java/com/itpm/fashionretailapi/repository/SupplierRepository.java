package com.itpm.fashionretailapi.repository;

import com.itpm.fashionretailapi.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier,Long> {
}
