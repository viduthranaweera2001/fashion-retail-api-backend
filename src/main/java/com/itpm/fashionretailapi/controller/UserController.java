package com.itpm.fashionretailapi.controller;

import com.itpm.fashionretailapi.controller.dto.UserRequestDto;
import com.itpm.fashionretailapi.controller.request.CreateUserRequest;
import com.itpm.fashionretailapi.controller.response.CreateUserResponse;
import com.itpm.fashionretailapi.controller.response.IdResponse;
import com.itpm.fashionretailapi.exception.CustomerNotFoundException;
import com.itpm.fashionretailapi.model.User;
import com.itpm.fashionretailapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {

    private UserService userService;
//    public static  String uploadDirectory=System.getProperty("user.dir") + "src/main/webapp/images";
    public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/webapp/images";

    @PostMapping("/users")
    public CreateUserResponse createUsers(@RequestBody UserRequestDto userRequestDto){
        return userService.createUser(userRequestDto);
    }

    @PostMapping("/user01")
    public User createUser(@ModelAttribute User user, @RequestParam ("image") MultipartFile file) throws IOException {
        String originalFileName = file.getOriginalFilename();
        Path fileNameAndPath=Paths.get(uploadDirectory,originalFileName);
        Files.write(fileNameAndPath,file.getBytes());
        user.setAvatar(originalFileName);
        User savedUserData = userService.createUser01(user);
        return savedUserData;
    }

    @GetMapping("/users/{id}")
    public CreateUserResponse getUsersById(@PathVariable Long id) throws CustomerNotFoundException {
        return userService.getUserDetailsById(id);
    }

    @GetMapping("/users")
    public List<CreateUserResponse> getUsersList() throws CustomerNotFoundException{
        return userService.listOfEmployees();
    }
    @PutMapping("/users/{id}")
    public CreateUserResponse updateUser(@PathVariable Long id, @RequestBody CreateUserRequest createUserRequest) throws CustomerNotFoundException{
        return userService.updateDetails(id,createUserRequest);
    }
    @DeleteMapping("/users/{id}")
    public IdResponse deleteUser(@PathVariable Long id) throws CustomerNotFoundException{
       return userService.deleteUser(id);
    }
}
