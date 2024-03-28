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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
public class ProductCategoryController {
    private ProductCategoryService productCategoryService;
    private ModelMapper modelMapper;
    @PostMapping(value = "/productCategories",headers = "version=v1")
    public ResponseEntity<ProductCategoryResponse> createProductCategory(@RequestBody ProductCategoryRequset productCategoryRequset){
        ProductCategoryRequestDto productCategoryRequestDto = modelMapper.map(productCategoryRequset,ProductCategoryRequestDto.class);
        ProductCategoryResponse createdCategory = productCategoryService.createCategory(productCategoryRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }

    @GetMapping("/productCategories")
    public ResponseEntity<List<ProductCategoryRequestDto>> getAllProductCategory()throws ProductCategoryNotFoundException{
        List<ProductCategoryRequestDto> productCategoryRequestDtoList = productCategoryService.getAllCategory();
        return ResponseEntity.status(HttpStatus.OK).body(productCategoryRequestDtoList);
    }

    @DeleteMapping(value = "/productCategories/{category-id}",headers = "version=v1")
    public ProductCategoryResponse deleteProductCategory(@PathVariable("category-id") Long id)throws ProductCategoryNotFoundException{
        return productCategoryService.deleteCategory(id);
    }
}
