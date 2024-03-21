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

@AllArgsConstructor
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private ModelMapper modelMapper;
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public ProductCategoryResponse createCategory(ProductCategoryRequestDto productCategoryRequestDto){
        ProductCategory productCategory = modelMapper.map(productCategoryRequestDto,ProductCategory.class);
        productCategoryRepository.save(productCategory);

        ProductCategoryResponse productCategoryResponse = new ProductCategoryResponse();
        productCategoryResponse.setResponseMsg(productCategory.getName()+" Created with id "+productCategory.getId());

        return productCategoryResponse;
    }

    @Override
    public ProductCategoryResponse deleteCategory(Long id)throws ProductCategoryNotFoundException{
        ProductCategory productCategory = productCategoryRepository.findById(id).orElseThrow(
                ()->new ProductCategoryNotFoundException("Product category not found !")
        );
        productCategoryRepository.deleteById(id);
        ProductCategoryResponse productCategoryResponse = new ProductCategoryResponse();
        productCategoryResponse.setResponseMsg(productCategory.getName()+" deleted!");
        System.out.println(productCategoryResponse.getResponseMsg());

        return productCategoryResponse;
    }
}
