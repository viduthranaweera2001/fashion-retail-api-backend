package com.itpm.fashionretailapi.exception;

public class NotFoundException extends Exception{
    public NotFoundException(String errorMsg){
        super(errorMsg);
    }
}
