package com.itpm.fashionretailapi.controller;

import com.itpm.fashionretailapi.controller.dto.DeliveryRequestDto;
import com.itpm.fashionretailapi.controller.request.DeliveryRequest;
import com.itpm.fashionretailapi.controller.response.DeliveryResponse;
import com.itpm.fashionretailapi.controller.response.IdResponse;
import com.itpm.fashionretailapi.exception.CustomerNotFoundException;
import com.itpm.fashionretailapi.exception.NotFoundException;
import com.itpm.fashionretailapi.exception.OrderNotFoundException;
import com.itpm.fashionretailapi.service.impl.DeliveryServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class DeliveryActionPlacedController {

    private DeliveryServiceImpl deliveryService;

    @PostMapping("/deliveries/{orderId}")
    public DeliveryResponse createDeliveryActionPlaced(@RequestBody DeliveryRequest deliveryRequest, @PathVariable("orderId") Long orderId) throws OrderNotFoundException {

        return deliveryService.saveDelivery(deliveryRequest,orderId);
    }

    @GetMapping("/deliveries")
    public List<DeliveryResponse> getAllDeliveryDetails() throws OrderNotFoundException{
        return deliveryService.getAllDeliveryDetails();
    }

    @GetMapping("/deliveries/{deliveryId}")
    public DeliveryResponse getDeliveryDetailsById(@PathVariable("deliveryId") Long id) throws NotFoundException {
        return  deliveryService.getDeliveryOrderDetailsById(id);
    }

    @PutMapping("/deliveries/{deliveryId}")
    public DeliveryResponse updateDeliveryDetails(@PathVariable("deliveryId") Long id,@RequestBody DeliveryRequestDto deliveryRequestDto) throws NotFoundException{
        return deliveryService.updateDetails(id,deliveryRequestDto);
    }

    @DeleteMapping("/deliveries/{deliveryId}")
    public IdResponse deleteDeliveryDetails(@PathVariable("deliveryId") Long id) throws NotFoundException{
        return deliveryService.deleteDeliveryDetails(id);
    }
}
