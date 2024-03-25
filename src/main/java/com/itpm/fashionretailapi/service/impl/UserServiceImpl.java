package com.itpm.fashionretailapi.service.impl;

import com.itpm.fashionretailapi.controller.dto.UserRequestDto;
import com.itpm.fashionretailapi.controller.request.UserRequest;
import com.itpm.fashionretailapi.controller.response.UserResponse;
import com.itpm.fashionretailapi.controller.response.IdResponse;
import com.itpm.fashionretailapi.exception.CustomerNotFoundException;
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
    public UserResponse createUser(UserRequestDto userRequestDto) {
        User user= modelMapper.map(userRequestDto, User.class);
        userRepository.save(user);

        UserResponse userResponse = modelMapper.map(user, UserResponse.class);
        return userResponse;
    }

    @Override
    public User createUser01(User user) {
        User savedUser = userRepository.save(user);
        return savedUser;
    }


//    @Override
//    public UserResponse getUserDetailsById(Long id) throws CustomerNotFoundException {
//        User user = userRepository.findById(id).orElseThrow(
//                () ->new CustomerNotFoundException("Customer  Not Found With Id "+id)
//        );
//
////        UserResponse createUserResponse = modelMapper.map(user, UserResponse.class);
//
//        UserResponse userResponse = new UserResponse();
//
//        userResponse.setId(user.getId());
//        userResponse.setName(user.getName());
//        userResponse.setEmail(user.getEmail());
//        userResponse.setPassword(user.getPassword());
//        userResponse.setAvatar(user.getAvatar());
//        return userResponse;
//    }


    @Override
    public User getUserDetailsById(Long id) throws CustomerNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found With Id " + id));
    }

    @Override
    public List<UserResponse> listOfEmployees() throws CustomerNotFoundException{
        List<User> usersList = userRepository.findAll();
        if(usersList.isEmpty()){
            throw new CustomerNotFoundException("No Any Customer Found Here");
        }
        return usersList.stream()
                .map((details -> modelMapper.map(details, UserResponse.class)))
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse updateDetails(Long id, UserRequest userRequest) throws CustomerNotFoundException {
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
        UserResponse createUserResponse = modelMapper.map(user, UserResponse.class);

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
