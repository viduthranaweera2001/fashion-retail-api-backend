package com.itpm.fashionretailapi.service;

import com.itpm.fashionretailapi.controller.request.DeliveryRequest;
import com.itpm.fashionretailapi.controller.response.DeliveryResponse;
import com.itpm.fashionretailapi.exception.CustomerNotFoundException;
import com.itpm.fashionretailapi.exception.NotFoundException;
import com.itpm.fashionretailapi.exception.OrderNotFoundException;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeliveryService  {
//    DeliveryResponse saveDelivery(Long cusId, Long orderId, DeliveryRequest deliveryRequest);
    DeliveryResponse saveDelivery(DeliveryRequest deliveryRequest, Long orderId) throws OrderNotFoundException;

    List<DeliveryResponse> getAllDeliveryDetails() throws OrderNotFoundException;

    DeliveryResponse getDeliveryOrderDetailsById(Long id) throws NotFoundException;


}
