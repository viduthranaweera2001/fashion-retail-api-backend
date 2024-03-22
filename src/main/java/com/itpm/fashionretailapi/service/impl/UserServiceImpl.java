package com.itpm.fashionretailapi.service.impl;

import com.itpm.fashionretailapi.controller.dto.UserRequestDto;
import com.itpm.fashionretailapi.controller.request.CreateUserRequest;
import com.itpm.fashionretailapi.controller.response.CreateUserResponse;
import com.itpm.fashionretailapi.controller.response.IdResponse;
import com.itpm.fashionretailapi.controller.response.ProductCategoryResponse;
import com.itpm.fashionretailapi.exception.CustomerNotFoundException;
import com.itpm.fashionretailapi.exception.ProductCategoryNotFoundException;
import com.itpm.fashionretailapi.model.ProductCategory;
import com.itpm.fashionretailapi.model.User;
import com.itpm.fashionretailapi.repository.UserRepository;
import com.itpm.fashionretailapi.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    @Override
    public CreateUserResponse createUser(UserRequestDto userRequestDto) {
        User user= modelMapper.map(userRequestDto, User.class);
        userRepository.save(user);

        CreateUserResponse userResponse = modelMapper.map(user, CreateUserResponse.class);
        return userResponse;
    }

    @Override
    public User createUser01(User user) {
        User savedUser = userRepository.save(user);
        return savedUser;
    }


    @Override
    public CreateUserResponse getUserDetailsById(Long id) throws CustomerNotFoundException {
        User user = userRepository.findById(id).orElseThrow(
                () ->new CustomerNotFoundException("Customer  Not Found With Id "+id)
        );

        CreateUserResponse createUserResponse = modelMapper.map(user,CreateUserResponse.class);
        return createUserResponse;
    }


    @Override
    public List<CreateUserResponse> listOfEmployees() throws CustomerNotFoundException{
        List<User> usersList = userRepository.findAll();
        if(usersList.isEmpty()){
            throw new CustomerNotFoundException("No Any Customer Found Here");
        }
        return usersList.stream()
                .map((details -> modelMapper.map(details,CreateUserResponse.class)))
                .collect(Collectors.toList());
    }

    @Override
    public CreateUserResponse updateDetails(Long id, CreateUserRequest userRequest) throws CustomerNotFoundException {
        User user = userRepository.findById(id).orElseThrow(
                () -> new CustomerNotFoundException("Customer  Not Found With Id "+id)
        );

        // Update user details with values from CreateUserRequest
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());

        // Save the updated user
        userRepository.save(user);

        // Map the updated user entity to CreateUserResponse
        CreateUserResponse createUserResponse = modelMapper.map(user, CreateUserResponse.class);

        return createUserResponse;

    }

    @Override
    public IdResponse deleteUser(Long id) throws CustomerNotFoundException {
        User user = userRepository.findById(id).orElseThrow(
                () -> new CustomerNotFoundException("Customer  Not Found With Id "+id)
        );
        userRepository.deleteById(id);
        IdResponse idResponse = new IdResponse();
        idResponse.setMessage(user.getName()+" Deleted!");

        return idResponse;
    }



}
