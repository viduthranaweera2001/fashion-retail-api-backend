package com.itpm.fashionretailapi.controller.response;

import com.itpm.fashionretailapi.model.User;
import lombok.Builder;
import lombok.Data;

@Data
public class OrderResponse {

    private String name;
    private String phoneNumber;
    private String address;
    private String city;
    private String postalCode;
    private Float shippingPrice;
    private Float TotalPrice;

}
