package com.itpm.fashionretailapi.controller.request;

import lombok.Data;

@Data
public class UserLoginRequest {
    private String email;
    private String password;
}
