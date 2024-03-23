package com.itpm.fashionretailapi.service.impl;

import com.itpm.fashionretailapi.controller.request.UserLoginRequest;
import com.itpm.fashionretailapi.controller.response.AuthenticationResponse;
import com.itpm.fashionretailapi.model.User;
import com.itpm.fashionretailapi.repository.LoginRepository;
import com.itpm.fashionretailapi.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService {
    LoginRepository loginRepository;
    public AuthenticationResponse authenticateUser(UserLoginRequest loginRequest){
        User user=loginRepository.findByEmailAndPassword(loginRequest.getEmail(),loginRequest.getPassword()).orElse(null);

            if(user == null){
                return new AuthenticationResponse(null,false);
            }
    return new AuthenticationResponse(user.getId(),true);
    }
}
