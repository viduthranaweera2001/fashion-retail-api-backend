package com.itpm.fashionretailapi.controller.response;

import lombok.Data;

@Data
public class CreateUserResponse {
    private String name;
    private String email;
    private String password;
    private String avatar;

}
