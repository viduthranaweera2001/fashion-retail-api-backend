package com.itpm.fashionretailapi.controller.dto;

import lombok.Data;

@Data
public class UserRequestDto {
    private String name;
    private String email;
    private String password;
//    private String avatar;
}
