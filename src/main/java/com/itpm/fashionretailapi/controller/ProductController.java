package com.itpm.fashionretailapi.controller;

import com.itpm.fashionretailapi.controller.dto.ProductRequestDto;
import com.itpm.fashionretailapi.controller.request.ProductRequest;
import com.itpm.fashionretailapi.controller.response.ProductResponse;
import com.itpm.fashionretailapi.exception.ProductCategoryNotFoundException;
import com.itpm.fashionretailapi.service.ProductService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class ProductController {
    private ModelMapper modelMapper;
    private ProductService productService;

    @PostMapping("/productCategories/{category-id}/products")
    public ResponseEntity<ProductResponse> createProduct(@PathVariable("category-id") Long id, @RequestBody ProductRequest productRequest)throws ProductCategoryNotFoundException{
        ProductRequestDto productRequestDto = modelMapper.map(productRequest,ProductRequestDto.class);
        ProductResponse productResponse = productService.createProduct(id,productRequestDto);
         return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);
    }
}
