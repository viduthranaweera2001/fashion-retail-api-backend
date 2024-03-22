package com.itpm.fashionretailapi.service;

import com.itpm.fashionretailapi.controller.dto.UserRequestDto;
import com.itpm.fashionretailapi.controller.request.CreateUserRequest;
import com.itpm.fashionretailapi.controller.response.CreateUserResponse;
import com.itpm.fashionretailapi.controller.response.IdResponse;
import com.itpm.fashionretailapi.exception.CustomerNotFoundException;
import com.itpm.fashionretailapi.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    CreateUserResponse createUser(UserRequestDto userRequestDto);

    User createUser01(User user);

    CreateUserResponse getUserDetailsById(Long id) throws CustomerNotFoundException;

    List<CreateUserResponse> listOfEmployees() throws CustomerNotFoundException;

    CreateUserResponse updateDetails(Long id, CreateUserRequest userRequest) throws CustomerNotFoundException;

    IdResponse deleteUser(Long id) throws CustomerNotFoundException;
}
