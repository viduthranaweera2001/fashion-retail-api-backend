package com.itpm.fashionretailapi.service.impl;

import com.itpm.fashionretailapi.controller.dto.ProductRequestDto;
import com.itpm.fashionretailapi.controller.response.ProductRespo;
import com.itpm.fashionretailapi.controller.response.ProductResponse;
import com.itpm.fashionretailapi.exception.ProductCategoryNotFoundException;
import com.itpm.fashionretailapi.exception.ProductNotFoundException;
import com.itpm.fashionretailapi.model.Product;
import com.itpm.fashionretailapi.model.ProductCategory;
import com.itpm.fashionretailapi.repository.ProductCategoryRepository;
import com.itpm.fashionretailapi.repository.ProductRepository;
import com.itpm.fashionretailapi.service.ProductService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<ProductRespo> getAllProducts() throws ProductNotFoundException {
        List<Product> productList = productRepository.findAll();

        if (productList.isEmpty())
            throw new ProductNotFoundException("Products Not Found");

        return productList.stream()
                .map(product -> modelMapper.map(product,ProductRespo.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductRespo> getProductByCatId(Long id) throws ProductCategoryNotFoundException, ProductNotFoundException {
        ProductCategory productCategory = productCategoryRepository.findById(id).orElseThrow(
                ()->new ProductCategoryNotFoundException("Product Category Not Found with "+id)
        );

        List<Product> productList = productRepository.getProductsByProductCategory(productCategory);
        if(productList.isEmpty())
            throw  new ProductNotFoundException("Product Not found with "+productCategory.getName()+" category");

        return productList.stream()
                .map(product -> modelMapper.map(product,ProductRespo.class))
                .collect(Collectors.toList());
    }
}
