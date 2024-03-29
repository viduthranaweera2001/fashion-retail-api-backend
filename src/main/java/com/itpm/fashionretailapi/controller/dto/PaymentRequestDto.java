package com.itpm.fashionretailapi.controller.dto;

import lombok.Data;

@Data
public class PaymentRequestDto {
    private String cardHolderName;
    private String cardNo;
    private String expDate;
    private Integer cvvNo;
}
