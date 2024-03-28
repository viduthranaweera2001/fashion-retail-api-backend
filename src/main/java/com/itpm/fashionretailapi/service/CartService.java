package com.itpm.fashionretailapi.service;

import com.itpm.fashionretailapi.controller.dto.CartDto;
import com.itpm.fashionretailapi.controller.response.CartResponse;
import com.itpm.fashionretailapi.exception.ProductNotFound;
import com.itpm.fashionretailapi.model.Product;
import com.itpm.fashionretailapi.model.User;

public interface CartService {
    CartResponse additems(Long id,CartDto cartDto) throws ProductNotFound;

//    CartResponse deleteitems(Long id,CartDto cartDto) throws ProductNotFound;
}
