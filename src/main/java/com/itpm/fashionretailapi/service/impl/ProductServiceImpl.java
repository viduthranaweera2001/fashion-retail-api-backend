package com.itpm.fashionretailapi.service.impl;

import com.itpm.fashionretailapi.controller.dto.ProductRequestDto;
import com.itpm.fashionretailapi.controller.response.ProductRespo;
import com.itpm.fashionretailapi.controller.response.ProductResponse;
import com.itpm.fashionretailapi.exception.ProductCategoryNotFoundException;
import com.itpm.fashionretailapi.exception.ProductNotFoundException;
import com.itpm.fashionretailapi.exception.SupplierNotFoundException;
import com.itpm.fashionretailapi.model.Product;
import com.itpm.fashionretailapi.model.ProductCategory;
import com.itpm.fashionretailapi.model.Supplier;
import com.itpm.fashionretailapi.repository.ProductCategoryRepository;
import com.itpm.fashionretailapi.repository.ProductRepository;
import com.itpm.fashionretailapi.repository.SupplierRepository;
import com.itpm.fashionretailapi.service.ProductService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.file.ProviderNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private ProductCategoryRepository productCategoryRepository;
    private SupplierRepository supplierRepository;
    private ModelMapper modelMapper;
    @Override
    public ProductResponse createProduct(Long id, ProductRequestDto productRequestDto) throws ProductCategoryNotFoundException, SupplierNotFoundException {

        ProductCategory productCategory = productCategoryRepository.findById(id).orElseThrow(
                ()->new ProductCategoryNotFoundException("Product Category not found!")
        );

        Supplier supplier = supplierRepository.findByName(productRequestDto.getSup_name());

        if(supplier == null)
            throw new SupplierNotFoundException("Supplier not found with name "+productRequestDto.getSup_name());

        Product product = modelMapper.map(productRequestDto,Product.class);
        product.setProductCategory(productCategory);
        product.setSupplier(supplier);

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

    @Override
    public ProductResponse updateProductByPID(Long id, ProductRequestDto productRequestDto)throws ProductNotFoundException {
        Product product = productRepository.findById(id).orElseThrow(
                ()->new ProductNotFoundException("Product Not Found!")
        );

        if(!productRequestDto.getPrice().isNaN())
            product.setPrice(productRequestDto.getPrice());
        if(!productRequestDto.getStock().describeConstable().isEmpty()) {
            product.setStock(productRequestDto.getStock());
        }

        productRepository.save(product);

        return ProductResponse.builder()
                .responseMsg("product updated with id "+product.getId())
                .build();
    }
}
