package com.itpm.fashionretailapi.service.impl;

import com.itpm.fashionretailapi.controller.dto.PaymentRequestDto;
import com.itpm.fashionretailapi.controller.response.IdResponse;
import com.itpm.fashionretailapi.exception.OrderNotFoundException;
import com.itpm.fashionretailapi.exception.PaymentIncorrectException;
import com.itpm.fashionretailapi.model.Order;
import com.itpm.fashionretailapi.model.Payment;
import com.itpm.fashionretailapi.model.PaymentCard;
import com.itpm.fashionretailapi.model.Status;
import com.itpm.fashionretailapi.repository.OrderRepository;
import com.itpm.fashionretailapi.repository.PaymentCardRepository;
import com.itpm.fashionretailapi.repository.PaymentRepository;
import com.itpm.fashionretailapi.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private PaymentCardRepository paymentCardRepository;
    private PaymentRepository paymentRepository;
    private OrderRepository orderRepository;
    @Override
    public IdResponse createPayment(Long id, PaymentRequestDto paymentRequestDto) throws OrderNotFoundException, PaymentIncorrectException {

        Order order = orderRepository.findById(id).orElseThrow(
                ()->new OrderNotFoundException("Order Not found with id "+id)
        );

        PaymentCard paymentCard = paymentCardRepository.findByUser(order.getUser());

        if(!paymentRequestDto.getCardNo().equals(paymentCard.getCardNo()))
            throw new PaymentIncorrectException("Your entered card number is incorrect!");
        if(!paymentRequestDto.getExpDate().equals(paymentCard.getExpDate()))
            throw new PaymentIncorrectException("Your entered exp date is incorrect!");
        if(!paymentRequestDto.getCvvNo().equals(paymentCard.getCvvNo()))
            throw new PaymentIncorrectException("Your entered cvv no is incorrect!");

        Payment payment = new Payment();
        payment.setUser(order.getUser());
        payment.setOrder(order);
        payment.setStatus(Status.SUCCESS);
        payment.setPrice(order.getTotalPrice());

        paymentRepository.save(payment);

        return IdResponse.builder()
                .message("Payment Successful")
                .build();
    }
}
