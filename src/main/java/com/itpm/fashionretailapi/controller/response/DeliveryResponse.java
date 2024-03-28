package com.itpm.fashionretailapi.controller.response;

import com.itpm.fashionretailapi.model.Order;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeliveryResponse {
    private Long id;
    private String address;
    private String  email;
    private String phoneNumber;

    private String deliveryPerson;
    private String user;
//    private Order  order;




}
