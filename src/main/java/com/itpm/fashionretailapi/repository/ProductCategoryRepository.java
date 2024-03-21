package com.itpm.fashionretailapi.repository;

import com.itpm.fashionretailapi.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Long> {
}
