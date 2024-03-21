package com.itpm.fashionretailapi.exception;

public class CustomerNotFoundException extends Exception{
    public CustomerNotFoundException (String errMsg){
        super(errMsg);
    }
}
