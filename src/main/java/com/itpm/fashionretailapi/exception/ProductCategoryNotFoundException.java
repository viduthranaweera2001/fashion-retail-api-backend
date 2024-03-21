package com.itpm.fashionretailapi.exception;

public class ProductCategoryNotFoundException extends NotFoundException{
    public ProductCategoryNotFoundException(String errMsg){
        super(errMsg);
    }
}
