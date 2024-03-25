package com.itpm.fashionretailapi.controller;

import com.itpm.fashionretailapi.controller.dto.UserRequestDto;
import com.itpm.fashionretailapi.controller.request.UserRequest;
import com.itpm.fashionretailapi.controller.response.UserResponse;
import com.itpm.fashionretailapi.controller.response.IdResponse;
import com.itpm.fashionretailapi.exception.CustomerNotFoundException;
import com.itpm.fashionretailapi.model.User;
import com.itpm.fashionretailapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public UserResponse createUsers(@RequestBody UserRequestDto userRequestDto){
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

//    @GetMapping("/users/{id}")
//    public UserResponse getUsersById(@PathVariable Long id) throws CustomerNotFoundException {
//        return userService.getUserDetailsById(id);
//    }
    @GetMapping("/users01/{id}")
    public User getUserById(@PathVariable Long id) throws CustomerNotFoundException{
        return userService.getUserDetailsById(id);
    }

    @GetMapping("/users")
    public List<UserResponse> getUsersList() throws CustomerNotFoundException{
        return userService.listOfEmployees();
    }
    @PutMapping("/users/{id}")
    public UserResponse updateUser(@PathVariable Long id, @RequestBody UserRequest createUserRequest) throws CustomerNotFoundException{
        return userService.updateDetails(id,createUserRequest);
    }
    @DeleteMapping("/users/{id}")
    public IdResponse deleteUser(@PathVariable Long id) throws CustomerNotFoundException{
       return userService.deleteUser(id);
    }

    //Fetching the image of the particular student
    @GetMapping("/users/getProfileImage/{id}")
    public ResponseEntity<Resource> getProfileImage(@PathVariable Long id) throws CustomerNotFoundException,IOException{
//        User user = userService.getUserDetailsById(id);
        User user =userService.getUserDetailsById(id);
        Path imagePath = Paths.get(uploadDirectory, user.getProfileImage());
        Resource resource = new FileSystemResource(imagePath.toFile());
        String contentType = Files.probeContentType(imagePath);

        return  ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body(resource);
    }

    //
}
