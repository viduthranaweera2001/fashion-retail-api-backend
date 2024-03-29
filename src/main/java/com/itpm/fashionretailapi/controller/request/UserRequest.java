package com.itpm.fashionretailapi.controller.request;

import lombok.Data;

@Data
public class UserRequest {
    private String name;
    private String email;
    private String password;
    private String role;
//    private String avatar;

}
