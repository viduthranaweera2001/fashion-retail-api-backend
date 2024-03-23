package com.itpm.fashionretailapi.service.impl;

import com.itpm.fashionretailapi.controller.dto.ProductRequestDto;
import com.itpm.fashionretailapi.controller.response.ProductResponse;
import com.itpm.fashionretailapi.exception.ProductCategoryNotFoundException;
import com.itpm.fashionretailapi.model.Product;
import com.itpm.fashionretailapi.model.ProductCategory;
import com.itpm.fashionretailapi.repository.ProductCategoryRepository;
import com.itpm.fashionretailapi.repository.ProductRepository;
import com.itpm.fashionretailapi.service.ProductService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private ProductCategoryRepository productCategoryRepository;
    private ModelMapper modelMapper;
    @Override
    public ProductResponse createProduct(Long id, ProductRequestDto productRequestDto)throws ProductCategoryNotFoundException {

        ProductCategory productCategory = productCategoryRepository.findById(id).orElseThrow(
                ()->new ProductCategoryNotFoundException("Product Category not found!")
        );

        Product product = modelMapper.map(productRequestDto,Product.class);
        product.setProductCategory(productCategory);

        productRepository.save(product);

        return ProductResponse.builder()
                .responseMsg(product.getName()+" Created with id: "+product.getId())
                .build();
    }
}
