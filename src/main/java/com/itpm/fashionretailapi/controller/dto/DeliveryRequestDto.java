package com.itpm.fashionretailapi.controller.dto;

import lombok.Data;

@Data
public class DeliveryRequestDto {
    private Long deliveryPersonId;
    private Long orderId;
    private String deliveryPersonName;
    private String customerName;
    private String customerEmail;
    private String phoneNo;

}
