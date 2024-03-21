package com.itpm.fashionretailapi.service;

import com.itpm.fashionretailapi.controller.dto.ProductCategoryRequestDto;
import com.itpm.fashionretailapi.controller.response.ProductCategoryResponse;
import com.itpm.fashionretailapi.exception.ProductCategoryNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface ProductCategoryService {
    ProductCategoryResponse createCategory(ProductCategoryRequestDto productCategoryRequestDto);
    ProductCategoryResponse deleteCategory(Long id)throws ProductCategoryNotFoundException;
}
