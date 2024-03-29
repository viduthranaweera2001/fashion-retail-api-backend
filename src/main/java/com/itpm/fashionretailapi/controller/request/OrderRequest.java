package com.itpm.fashionretailapi.controller.request;

import com.itpm.fashionretailapi.model.Status;
import lombok.Data;

@Data
public class OrderRequest {
    private String userName;
    private String address;
    private String city;
    private String phoneNumber;
    private String postalCode;
    private String status;
}
