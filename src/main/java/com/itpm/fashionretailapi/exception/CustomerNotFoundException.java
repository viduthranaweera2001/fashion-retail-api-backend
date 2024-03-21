package com.itpm.fashionretailapi.exception;

public class CustomerNotFoundException extends NotFoundException{
    public CustomerNotFoundException (String errorMsg){
        super(errorMsg);
    }
}
