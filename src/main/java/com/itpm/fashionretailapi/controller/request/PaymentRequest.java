package com.itpm.fashionretailapi.controller.request;

import lombok.Data;

@Data
public class PaymentRequest {
    private String cardHolderName;
    private String cardNo;
    private String expDate;
    private Integer cvvNo;
}
