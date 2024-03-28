package com.itpm.fashionretailapi.controller.response;

import com.itpm.fashionretailapi.model.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderResponse {
    private Long id;
    private Float shippingPrice;
    private String address;
    private String city;
    private String phoneNumber;
    private String postalCode;
    private Float price;
    private User user;
}
