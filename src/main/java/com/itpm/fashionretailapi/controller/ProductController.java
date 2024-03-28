package com.itpm.fashionretailapi.controller;

import com.itpm.fashionretailapi.controller.dto.ProductRequestDto;
import com.itpm.fashionretailapi.controller.request.ProductRequest;
import com.itpm.fashionretailapi.controller.response.ProductRespo;
import com.itpm.fashionretailapi.controller.response.ProductResponse;
import com.itpm.fashionretailapi.exception.ProductCategoryNotFoundException;
import com.itpm.fashionretailapi.exception.ProductNotFoundException;
import com.itpm.fashionretailapi.exception.SupplierNotFoundException;
import com.itpm.fashionretailapi.service.ProductService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin("http://localhost:3000")
public class ProductController {
    private ModelMapper modelMapper;
    private ProductService productService;

    @PostMapping("/productCategories/{category-id}/products")
    public ResponseEntity<ProductResponse> createProduct(@PathVariable("category-id") Long id, @RequestBody ProductRequest productRequest)throws ProductCategoryNotFoundException, SupplierNotFoundException {
        ProductRequestDto productRequestDto = modelMapper.map(productRequest,ProductRequestDto.class);
        ProductResponse productResponse = productService.createProduct(id,productRequestDto);
         return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);
    }

    @GetMapping(value = "/products")
    public ResponseEntity<List<ProductRespo>> getAllProducts()throws ProductNotFoundException {
        List<ProductRespo> productRespoList = productService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(productRespoList);
    }
    @GetMapping("/products/{product_id}")
    public ResponseEntity<ProductRespo> getProductById(@PathVariable ("product_id")Long id )throws ProductNotFoundException{
        ProductRespo productRespo = productService.getProductById(id);
        return ResponseEntity.status(HttpStatus.OK).body(productRespo);
    }

    @GetMapping(value = "/productsCategories/{cat_id}/products")
    public ResponseEntity<List<ProductRespo>> getProductByCatId(@PathVariable ("cat_id") Long id)throws ProductCategoryNotFoundException,ProductNotFoundException{
        List<ProductRespo> productRespoList = productService.getProductByCatId(id);
        return ResponseEntity.status(HttpStatus.OK).body(productRespoList);
    }

    @PutMapping("/products/{product_id}")
    public ResponseEntity<ProductResponse> updateProductById(@PathVariable("product_id") Long id,@RequestBody ProductRequest productRequest)throws ProductNotFoundException{
        ProductRequestDto productRequestDto = modelMapper.map(productRequest,ProductRequestDto.class);
        ProductResponse productResponse = productService.updateProductByPID(id,productRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(productResponse);
    }
}
