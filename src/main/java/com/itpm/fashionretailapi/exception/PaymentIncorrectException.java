package com.itpm.fashionretailapi.exception;

public class PaymentIncorrectException extends NotFoundException{
    public PaymentIncorrectException(String errorMsg){super(errorMsg);}
}
