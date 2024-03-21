package com.itpm.fashionretailapi.service;

import com.itpm.fashionretailapi.controller.request.UserLoginRequest;
import com.itpm.fashionretailapi.controller.response.AuthenticationResponse;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    AuthenticationResponse authenticateUser(UserLoginRequest loginRequest);
}
