package com.itpm.fashionretailapi.exception;

import com.itpm.fashionretailapi.controller.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppControllerAdviser {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundException.class})
    public ErrorResponse handleException(Exception exception){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorMsg(exception.getMessage());

        return errorResponse;
    }
}
