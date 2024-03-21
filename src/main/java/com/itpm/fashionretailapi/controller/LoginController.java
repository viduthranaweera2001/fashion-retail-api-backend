package com.itpm.fashionretailapi.controller;

import com.itpm.fashionretailapi.controller.request.UserLoginRequest;
import com.itpm.fashionretailapi.controller.response.AuthenticationResponse;
import com.itpm.fashionretailapi.repository.LoginRepository;
import com.itpm.fashionretailapi.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/user/authenticate")
    public AuthenticationResponse authresponse(@RequestBody UserLoginRequest userLoginRequest){
        return loginService.authenticateUser(userLoginRequest);
    }
}
