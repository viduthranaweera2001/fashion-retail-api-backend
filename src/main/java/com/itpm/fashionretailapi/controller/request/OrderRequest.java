package com.itpm.fashionretailapi.controller.request;

import lombok.Data;

@Data
public class OrderRequest {
    private String address;
    private String city;
    private String phoneNumber;
    private String postalCode;
}
