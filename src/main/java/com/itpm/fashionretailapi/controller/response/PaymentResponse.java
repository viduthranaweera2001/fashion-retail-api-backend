package com.itpm.fashionretailapi.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentResponse {
    private String responseMsg;
}
