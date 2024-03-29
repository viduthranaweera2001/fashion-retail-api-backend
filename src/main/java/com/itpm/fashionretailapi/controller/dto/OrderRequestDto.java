package com.itpm.fashionretailapi.controller.dto;

import com.itpm.fashionretailapi.model.Status;
import lombok.Data;

@Data
public class OrderRequestDto {
    private String userName;
    private String address;
    private String city;
    private String phoneNumber;
    private String postalCode;
    private String status;
}
