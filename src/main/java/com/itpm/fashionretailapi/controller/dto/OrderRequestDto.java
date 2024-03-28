package com.itpm.fashionretailapi.controller.dto;

import lombok.Data;

@Data
public class OrderRequestDto {
    private String address;
    private String city;
    private String phoneNumber;
    private String postalCode;
}
