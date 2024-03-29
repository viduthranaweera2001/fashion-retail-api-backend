package com.itpm.fashionretailapi.service;

import com.itpm.fashionretailapi.controller.dto.PaymentRequestDto;
import com.itpm.fashionretailapi.controller.response.IdResponse;
import com.itpm.fashionretailapi.exception.OrderNotFoundException;
import com.itpm.fashionretailapi.exception.PaymentIncorrectException;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService{
    IdResponse createPayment(Long id, PaymentRequestDto paymentRequestDto)throws OrderNotFoundException, PaymentIncorrectException;
}
