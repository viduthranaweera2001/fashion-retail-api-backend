package com.itpm.fashionretailapi.controller;

import com.itpm.fashionretailapi.controller.dto.OrderRequestDto;
import com.itpm.fashionretailapi.controller.request.OrderRequest;
import com.itpm.fashionretailapi.controller.response.IdResponse;
import com.itpm.fashionretailapi.controller.response.OrderResponse;
import com.itpm.fashionretailapi.exception.CartNotFoundException;
import com.itpm.fashionretailapi.exception.CustomerNotFoundException;
import com.itpm.fashionretailapi.exception.OrderNotFoundException;
import com.itpm.fashionretailapi.service.OrderService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class OrderController {

    private ModelMapper modelMapper;
    private OrderService orderService;
    @PostMapping(value="/carts/{cart-id}/order")
    public ResponseEntity<IdResponse> createOrder(@PathVariable("cart-id") Long id, @RequestBody OrderRequest orderRequest)throws CartNotFoundException {
        OrderRequestDto orderRequestDto = modelMapper.map(orderRequest, OrderRequestDto.class);
        IdResponse idResponse = orderService.addOrder(id,orderRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(idResponse);
    }

    @GetMapping(value = "/users/{user-id}/orders")
    public ResponseEntity<List<OrderResponse>> getAllOrdersByUser(@PathVariable("user-id") Long Uid)throws CustomerNotFoundException,OrderNotFoundException{
        List<OrderResponse> orderResponseList = orderService.getOrdersByUser(Uid);
        return ResponseEntity.status(HttpStatus.OK).body(orderResponseList);
    }

    @PutMapping(value = "/orders/{order-id}")
    public ResponseEntity<IdResponse> updateStatus(@PathVariable("order-id")Long id,@RequestBody OrderRequest orderRequest)throws OrderNotFoundException {
        OrderRequestDto orderRequestDto = modelMapper.map(orderRequest,OrderRequestDto.class);
        IdResponse idResponse = orderService.updateStatus(id,orderRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(idResponse);
    }
}
