package com.itpm.fashionretailapi.service;

import com.itpm.fashionretailapi.controller.dto.OrderRequestDto;
import com.itpm.fashionretailapi.controller.response.IdResponse;
import com.itpm.fashionretailapi.exception.CartNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    IdResponse addOrder(Long id, OrderRequestDto orderRequestDto)throws CartNotFoundException;
}
