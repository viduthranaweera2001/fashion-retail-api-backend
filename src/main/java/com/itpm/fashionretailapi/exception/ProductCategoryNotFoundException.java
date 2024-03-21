package com.itpm.fashionretailapi.exception;

public class ProductCategoryNotFoundException extends Exception{
    public ProductCategoryNotFoundException(String errMsg){
        super(errMsg);
    }
}
