package com.itpm.fashionretailapi.controller;

import com.itpm.fashionretailapi.controller.dto.OrderRequestDto;
import com.itpm.fashionretailapi.controller.request.OrderRequest;
import com.itpm.fashionretailapi.controller.response.IdResponse;
import com.itpm.fashionretailapi.exception.CartNotFoundException;
import com.itpm.fashionretailapi.service.OrderService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class OrderController {

    private ModelMapper modelMapper;
    private OrderService orderService;
    @PostMapping("/carts/{cart-id}/order")
    public ResponseEntity<IdResponse> createOrder(@PathVariable("cart-id") Long id, @RequestBody OrderRequest orderRequest)throws CartNotFoundException {
        OrderRequestDto orderRequestDto = modelMapper.map(orderRequest, OrderRequestDto.class);
        IdResponse idResponse = orderService.addOrder(id,orderRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(idResponse);
    }

}
