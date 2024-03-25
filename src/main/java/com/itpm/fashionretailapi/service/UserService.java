package com.itpm.fashionretailapi.service;

import com.itpm.fashionretailapi.controller.dto.UserRequestDto;
import com.itpm.fashionretailapi.controller.request.UserRequest;
import com.itpm.fashionretailapi.controller.response.UserResponse;
import com.itpm.fashionretailapi.controller.response.IdResponse;
import com.itpm.fashionretailapi.exception.CustomerNotFoundException;
import com.itpm.fashionretailapi.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserResponse createUser(UserRequestDto userRequestDto);

    User createUser01(User user);

//    UserResponse getUserDetailsById(Long id) throws CustomerNotFoundException;
    User getUserDetailsById(Long id)throws CustomerNotFoundException;

    List<UserResponse> listOfEmployees() throws CustomerNotFoundException;

    UserResponse updateDetails(Long id, UserRequest userRequest) throws CustomerNotFoundException;

    IdResponse deleteUser(Long id) throws CustomerNotFoundException;
}
