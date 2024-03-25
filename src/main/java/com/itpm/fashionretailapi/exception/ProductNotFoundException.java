package com.itpm.fashionretailapi.exception;

public class ProductNotFoundException extends NotFoundException{
    public ProductNotFoundException(String errMsg){
        super(errMsg);
    }
}
