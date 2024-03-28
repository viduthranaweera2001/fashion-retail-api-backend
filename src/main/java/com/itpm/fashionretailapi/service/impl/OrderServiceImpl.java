package com.itpm.fashionretailapi.service.impl;

import com.itpm.fashionretailapi.controller.dto.OrderRequestDto;
import com.itpm.fashionretailapi.controller.response.IdResponse;
import com.itpm.fashionretailapi.exception.CartNotFoundException;
import com.itpm.fashionretailapi.model.Cart;
import com.itpm.fashionretailapi.model.Order;
import com.itpm.fashionretailapi.model.Status;
import com.itpm.fashionretailapi.model.User;
import com.itpm.fashionretailapi.repository.CartRepository;
import com.itpm.fashionretailapi.repository.OrderRepository;
import com.itpm.fashionretailapi.repository.UserRepository;
import com.itpm.fashionretailapi.service.OrderService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private ModelMapper modelMapper;
    private CartRepository cartRepository;
    private OrderRepository orderRepository;
    @Override
    public IdResponse addOrder(Long id, OrderRequestDto orderRequestDto) throws CartNotFoundException {
        Cart cart = cartRepository.findById(id).orElseThrow(
                ()->new CartNotFoundException("Cart is Empty!")
        );

        Order order = modelMapper.map(orderRequestDto, Order.class);
        order.setCart(cart);
        order.setStatus(Status.PROCESSING);
        order.setShippingPrice(Float.valueOf("194.00"));
        order.setTotalPrice((float) (194.00+ cart.getPrice()));

        orderRepository.save(order);

        return IdResponse.builder()
                .message(cart.getUser().getName()+" ordered "+cart.getProduct().getName())
                .build();
    }
}
