package com.itpm.fashionretailapi.controller;

import com.itpm.fashionretailapi.controller.dto.ProductCategoryRequestDto;
import com.itpm.fashionretailapi.controller.request.ProductCategoryRequset;
import com.itpm.fashionretailapi.controller.response.ProductCategoryResponse;
import com.itpm.fashionretailapi.exception.ProductCategoryNotFoundException;
import com.itpm.fashionretailapi.service.ProductCategoryService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ProductCategoryController {
    private ProductCategoryService productService;
    private ModelMapper modelMapper;
    @PostMapping(value = "/productCategories",headers = "version=v1")
    public ResponseEntity<ProductCategoryResponse> createProductCategory(@RequestBody ProductCategoryRequset productCategoryRequset)throws ProductCategoryNotFoundException {
        ProductCategoryRequestDto productCategoryRequestDto = modelMapper.map(productCategoryRequset,ProductCategoryRequestDto.class);
        ProductCategoryResponse createdCategory = productService.createCategory(productCategoryRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }
}
