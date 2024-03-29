package com.itpm.fashionretailapi.repository;

import com.itpm.fashionretailapi.model.Product;
import com.itpm.fashionretailapi.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> getProductsByProductCategory(ProductCategory productCategory);
}
