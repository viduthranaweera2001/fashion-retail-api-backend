package com.itpm.fashionretailapi.controller.request;

import lombok.Data;

@Data
public class SupplierRequest {
    private String name;
    private String nic;
    private String phoneNo;
    private String email;
}
