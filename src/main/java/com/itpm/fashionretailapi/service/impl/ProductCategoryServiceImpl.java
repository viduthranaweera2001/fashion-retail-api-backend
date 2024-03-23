package com.itpm.fashionretailapi.service.impl;

import com.itpm.fashionretailapi.controller.dto.ProductCategoryRequestDto;
import com.itpm.fashionretailapi.controller.response.ProductCategoryResponse;
import com.itpm.fashionretailapi.exception.ProductCategoryNotFoundException;
import com.itpm.fashionretailapi.model.ProductCategory;
import com.itpm.fashionretailapi.repository.ProductCategoryRepository;
import com.itpm.fashionretailapi.service.ProductCategoryService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private ModelMapper modelMapper;
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public ProductCategoryResponse createCategory(ProductCategoryRequestDto productCategoryRequestDto){
        ProductCategory productCategory = modelMapper.map(productCategoryRequestDto,ProductCategory.class);
        productCategoryRepository.save(productCategory);

        return ProductCategoryResponse.builder()
                .responseMsg(productCategory.getName()+" Created with id "+productCategory.getId())
                .build();
    }

    @Override
    public List<ProductCategoryRequestDto> getAllCategory()throws ProductCategoryNotFoundException {
        List<ProductCategory> productCategoryList = productCategoryRepository.findAll();

        if(productCategoryList.isEmpty())
            throw new ProductCategoryNotFoundException("Product Categories Not found!");

        return productCategoryList.stream()
                .map(productCategory -> modelMapper.map(productCategory,ProductCategoryRequestDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductCategoryResponse deleteCategory(Long id)throws ProductCategoryNotFoundException{
        ProductCategory productCategory = productCategoryRepository.findById(id).orElseThrow(
                ()->new ProductCategoryNotFoundException("Product category not found !")
        );
        productCategoryRepository.deleteById(id);

        return ProductCategoryResponse.builder()
                .responseMsg(productCategory.getName()+" Deleted!")
                .build();
    }
}
