package com.itpm.fashionretailapi.exception;

public class OrderNotFoundException extends NotFoundException{
    public OrderNotFoundException(String errorMsg){
        super(errorMsg);
    }
}
