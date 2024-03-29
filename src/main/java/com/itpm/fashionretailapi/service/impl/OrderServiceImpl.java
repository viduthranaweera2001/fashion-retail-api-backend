package com.itpm.fashionretailapi.service.impl;

import com.itpm.fashionretailapi.controller.dto.OrderRequestDto;
import com.itpm.fashionretailapi.controller.response.IdResponse;
import com.itpm.fashionretailapi.controller.response.OrderResponse;
import com.itpm.fashionretailapi.exception.CartNotFoundException;
import com.itpm.fashionretailapi.exception.CustomerNotFoundException;
import com.itpm.fashionretailapi.exception.OrderNotFoundException;
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

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private ModelMapper modelMapper;
    private CartRepository cartRepository;
    private OrderRepository orderRepository;
    private UserRepository userRepository;
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
        order.setUser(cart.getUser());

        order.setName(cart.getUser().getName());

        orderRepository.save(order);

        return IdResponse.builder()
                .message(cart.getUser().getName()+" ordered "+cart.getProduct().getName())
                .build();
    }

    @Override
    public List<OrderResponse> getOrdersByUser(Long Uid)throws CustomerNotFoundException,OrderNotFoundException{
        User user = userRepository.findById(Uid).orElseThrow(
                ()->new CustomerNotFoundException("user Not found with id "+Uid)
        );
        List<Order> orderList = orderRepository.findOrdersByUser(user);

        if (orderList.isEmpty())
            throw new OrderNotFoundException("Order Not Found");

        return orderList.stream()
                .map(order -> modelMapper.map(order,OrderResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public IdResponse updateStatus(Long id, OrderRequestDto orderRequestDto) throws OrderNotFoundException {
        Order order = orderRepository.findById(id).orElseThrow(
                ()->new OrderNotFoundException("Order Not found with id "+id)
        );

        if (orderRequestDto.getStatus().equalsIgnoreCase("DELIVERED"))
            order.setStatus(Status.DELIVERED);

        if (orderRequestDto.getStatus().equalsIgnoreCase("PROCESSING"))
            order.setStatus(Status.PROCESSING);

        orderRepository.save(order);

        return IdResponse.builder()
                .message("Order status updated to "+orderRequestDto.getStatus())
                .build();
    }
}
