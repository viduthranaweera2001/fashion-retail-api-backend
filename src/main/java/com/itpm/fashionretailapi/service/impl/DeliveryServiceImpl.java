package com.itpm.fashionretailapi.service.impl;

import com.itpm.fashionretailapi.controller.dto.DeliveryRequestDto;
import com.itpm.fashionretailapi.controller.request.DeliveryRequest;
import com.itpm.fashionretailapi.controller.response.DeliveryResponse;

import com.itpm.fashionretailapi.controller.response.IdResponse;
import com.itpm.fashionretailapi.exception.NotFoundException;
import com.itpm.fashionretailapi.exception.OrderNotFoundException;
import com.itpm.fashionretailapi.model.*;
import com.itpm.fashionretailapi.repository.DeliveryPersonRepository;
import com.itpm.fashionretailapi.repository.DeliveryRepository;
import com.itpm.fashionretailapi.repository.OrderRepository;

import com.itpm.fashionretailapi.repository.UserRepository;
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
    private UserRepository userRepository;
    @Override
    public DeliveryResponse saveDelivery(DeliveryRequest deliveryRequest, Long orderId) throws OrderNotFoundException {

        DeliveryPerson deliveryPerson = deliveryPersonRepository.findByName(deliveryRequest.getDeliveryPersonName());

        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new OrderNotFoundException("Order not found" + orderId));

        User customer = userRepository.findByName(deliveryRequest.getOrderPlacedCustomer());

        DeliveryActionPlaced deliveryActionPlaced = new DeliveryActionPlaced();

        deliveryActionPlaced.setDeliveryPerson(deliveryPerson);
        deliveryActionPlaced.setDeliveryPersonName(deliveryPerson.getName());
        deliveryActionPlaced.setAddress(order.getAddress());
        deliveryActionPlaced.setPhoneNo(order.getPhoneNumber());
        deliveryActionPlaced.setCustomerEmail(customer.getEmail());
        deliveryActionPlaced.setCustomerName(customer.getName());

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


    @Override
    public DeliveryResponse updateDetails(Long id, DeliveryRequestDto deliveryRequestDto) throws NotFoundException {

        DeliveryActionPlaced deliveryActionPlaced = deliveryRepository.findById(id).orElseThrow(
                () -> new NotFoundException(" No Such Delivery Found With Id "+id)
        );


        DeliveryPerson deliveryPerson = deliveryPersonRepository.findById(deliveryRequestDto.getDeliveryPersonId())
                .orElseThrow(() -> new NotFoundException("No Such DeliveryPerson Found With Id " + deliveryRequestDto.getDeliveryPersonId()));

        Order order = orderRepository.findById(deliveryRequestDto.getOrderId())
                .orElseThrow(() -> new NotFoundException("No Such Order Found With Id " + deliveryRequestDto.getOrderId()));

        deliveryActionPlaced.setDeliveryPerson(deliveryPerson);
        deliveryActionPlaced.setOrder(order);
        deliveryActionPlaced.setDeliveryPerson(deliveryPerson);
        deliveryActionPlaced.setDeliveryPersonName(deliveryRequestDto.getDeliveryPersonName());
        deliveryActionPlaced.setCustomerName(deliveryRequestDto.getCustomerName());
        deliveryActionPlaced.setAddress(deliveryActionPlaced.getAddress());
        deliveryActionPlaced.setPhoneNo(deliveryRequestDto.getPhoneNo());
        deliveryActionPlaced.setCustomerEmail(deliveryRequestDto.getCustomerEmail());

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
    public String deleteDeliveryDetails(Long id) throws NotFoundException {
        DeliveryActionPlaced deliveryActionPlaced = deliveryRepository.findById(id).orElseThrow(
                () -> new NotFoundException(" No Such Delivery Found With Id "+id)
        );

        deliveryRepository.deleteById(id);

        return ("Delivery ID "+id+" Details Delete Successfully");

    }
}
