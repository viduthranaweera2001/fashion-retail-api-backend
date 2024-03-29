package com.itpm.fashionretailapi.controller;

import com.itpm.fashionretailapi.controller.dto.PaymentRequestDto;
import com.itpm.fashionretailapi.controller.request.PaymentRequest;
import com.itpm.fashionretailapi.controller.response.IdResponse;
import com.itpm.fashionretailapi.exception.OrderNotFoundException;
import com.itpm.fashionretailapi.exception.PaymentIncorrectException;
import com.itpm.fashionretailapi.service.PaymentService;
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
public class PaymentController {
    private ModelMapper modelMapper;
    private PaymentService paymentService;
    @PostMapping(value = "/orders/{order_id}/payment")
    public ResponseEntity<IdResponse> createPayment(@PathVariable ("order_id")Long id, @RequestBody PaymentRequest paymentRequest)throws OrderNotFoundException, PaymentIncorrectException {
        PaymentRequestDto paymentRequestDto = modelMapper.map(paymentRequest,PaymentRequestDto.class);
        IdResponse idResponse = paymentService.createPayment(id,paymentRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(idResponse);
    }

}
