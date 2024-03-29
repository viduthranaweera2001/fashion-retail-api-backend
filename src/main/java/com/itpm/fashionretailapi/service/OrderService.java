package com.itpm.fashionretailapi.service;

import com.itpm.fashionretailapi.controller.dto.OrderRequestDto;
import com.itpm.fashionretailapi.controller.response.IdResponse;
import com.itpm.fashionretailapi.controller.response.OrderResponse;
import com.itpm.fashionretailapi.exception.CartNotFoundException;
import com.itpm.fashionretailapi.exception.CustomerNotFoundException;
import com.itpm.fashionretailapi.exception.OrderNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    IdResponse addOrder(Long id, OrderRequestDto orderRequestDto)throws CartNotFoundException;
    IdResponse updateStatus(Long id, OrderRequestDto orderRequestDto)throws OrderNotFoundException;
    List<OrderResponse> getOrdersByUser(Long Uid)throws CustomerNotFoundException,OrderNotFoundException;
}
