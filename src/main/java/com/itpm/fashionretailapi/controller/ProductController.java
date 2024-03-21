package com.itpm.fashionretailapi.controller;

import com.itpm.fashionretailapi.controller.dto.ProductCategoryRequestDto;
import com.itpm.fashionretailapi.controller.dto.ProductRequestDto;
import com.itpm.fashionretailapi.controller.request.ProductCategoryRequset;
import com.itpm.fashionretailapi.model.ProductCategory;
import com.itpm.fashionretailapi.service.ProductService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ProductController {
//    private ProductService productService;
    private ModelMapper modelMapper;
    @PostMapping(value = "/productCategories",headers = "version=v1")
    public ResponseEntity<ProductRequestDto> createProductCategory(@RequestBody ProductCategoryRequset productCategoryRequset){
        ProductCategoryRequestDto productCategoryRequestDto = modelMapper.map(productCategoryRequset,ProductCategoryRequestDto.class);
        return null;

    }
}
