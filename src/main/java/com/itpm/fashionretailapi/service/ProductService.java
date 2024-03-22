package com.itpm.fashionretailapi.service;

import com.itpm.fashionretailapi.controller.dto.ProductRequestDto;
import com.itpm.fashionretailapi.controller.response.ProductResponse;
import com.itpm.fashionretailapi.exception.ProductCategoryNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    ProductResponse createProduct(Long id, ProductRequestDto productRequestDto)throws ProductCategoryNotFoundException;
}
