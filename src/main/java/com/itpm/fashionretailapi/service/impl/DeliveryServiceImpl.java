package com.itpm.fashionretailapi.service.impl;

import com.itpm.fashionretailapi.controller.request.DeliveryRequest;
import com.itpm.fashionretailapi.controller.response.DeliveryResponse;

import com.itpm.fashionretailapi.exception.NotFoundException;
import com.itpm.fashionretailapi.exception.OrderNotFoundException;
import com.itpm.fashionretailapi.model.*;
import com.itpm.fashionretailapi.repository.DeliveryPersonRepository;
import com.itpm.fashionretailapi.repository.DeliveryRepository;
import com.itpm.fashionretailapi.repository.OrderRepository;

import com.itpm.fashionretailapi.service.DeliveryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
@AllArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    private OrderRepository orderRepository;
    private DeliveryRepository deliveryRepository;
    private DeliveryPersonRepository deliveryPersonRepository;
    @Override
    public DeliveryResponse saveDelivery(DeliveryRequest deliveryRequest, Long orderId) throws OrderNotFoundException {

        DeliveryPerson deliveryPerson = deliveryPersonRepository.findByName(deliveryRequest.getDeliveryPersonName());
        // Find order by ID
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new OrderNotFoundException("Order not found" + orderId));

        System.out.println(order.getAddress());
        System.out.println(order.getPhoneNumber());

        DeliveryActionPlaced deliveryActionPlaced = new DeliveryActionPlaced();

        deliveryActionPlaced.setDeliveryPerson(deliveryPerson);
        deliveryActionPlaced.setDeliveryPersonName(deliveryPerson.getName());
        deliveryActionPlaced.setAddress(order.getAddress());
        deliveryActionPlaced.setPhoneNo(order.getPhoneNumber());

        deliveryActionPlaced.setOrder(order);

        deliveryRepository.save(deliveryActionPlaced);

        DeliveryResponse deliveryResponse = DeliveryResponse.builder()
                .id(deliveryActionPlaced.getId())
                .address(deliveryActionPlaced.getAddress())
                .email(deliveryActionPlaced.getCustomerEmail())
                .phoneNumber(deliveryActionPlaced.getPhoneNo())
                .deliveryPerson(deliveryActionPlaced.getDeliveryPersonName())
                .user(deliveryActionPlaced.getCustomerName())
                .build();

        return deliveryResponse;
    }




    @Override
    public List<DeliveryResponse> getAllDeliveryDetails() throws OrderNotFoundException {
        List<DeliveryActionPlaced> deliveryActionPlacedList = deliveryRepository.findAll();
        if(deliveryActionPlacedList.isEmpty()){
            throw  new OrderNotFoundException("No any Delivered Order Details Find Here");
        }

        List<DeliveryResponse> deliveryResponses= deliveryActionPlacedList.stream()
                .map(deliveryActionPlaced -> DeliveryResponse.builder()
                        .id(deliveryActionPlaced.getId())
                        .address(deliveryActionPlaced.getAddress())
                        .email(deliveryActionPlaced.getCustomerEmail())
                        .phoneNumber(deliveryActionPlaced.getPhoneNo())
                        .deliveryPerson(deliveryActionPlaced.getDeliveryPersonName())
                        .user(deliveryActionPlaced.getCustomerName())
                        .build())
                .toList();
        return deliveryResponses;
    }

    @Override
    public DeliveryResponse getDeliveryOrderDetailsById(Long id) throws NotFoundException {
        DeliveryActionPlaced deliveryActionPlaced = deliveryRepository.findById(id).orElseThrow(
                () ->   new NotFoundException(" No Such Delivery Found With Id "+id)
        );

        DeliveryResponse deliveryResponse = DeliveryResponse.builder()
                .id(deliveryActionPlaced.getId())
                .address(deliveryActionPlaced.getAddress())
                .email(deliveryActionPlaced.getCustomerEmail())
                .phoneNumber(deliveryActionPlaced.getPhoneNo())
                .deliveryPerson(deliveryActionPlaced.getDeliveryPersonName())
                .user(deliveryActionPlaced.getCustomerName())
                .build();
        return deliveryResponse;
    }





}
