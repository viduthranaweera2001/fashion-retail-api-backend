package com.itpm.fashionretailapi.service;

import com.itpm.fashionretailapi.controller.dto.ProductRequestDto;
import com.itpm.fashionretailapi.controller.response.ProductRespo;
import com.itpm.fashionretailapi.controller.response.ProductResponse;
import com.itpm.fashionretailapi.exception.ProductCategoryNotFoundException;
import com.itpm.fashionretailapi.exception.ProductNotFoundException;
import com.itpm.fashionretailapi.exception.SupplierNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    ProductResponse createProduct(Long id, ProductRequestDto productRequestDto) throws ProductCategoryNotFoundException, SupplierNotFoundException;
    List<ProductRespo> getAllProducts()throws ProductNotFoundException;
    List<ProductRespo> getProductByCatId(Long id)throws ProductCategoryNotFoundException,ProductNotFoundException;

}
