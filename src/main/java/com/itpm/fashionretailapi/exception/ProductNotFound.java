package com.itpm.fashionretailapi.exception;

public class ProductNotFound extends NotFoundException{
    public ProductNotFound(String errMsg) {
        super(errMsg);
    }
}
