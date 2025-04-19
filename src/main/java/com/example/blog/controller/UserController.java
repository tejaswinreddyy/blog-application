package com.example.blog.controller;

import com.example.blog.entity.User;
import com.example.blog.service.UserService;
import com.example.blog.util.ResponseStructure;
import com.example.blog.util.ResponseBuilderFactory;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private ResponseBuilderFactory responseBuilder;

    @PostMapping("/users")
    public ResponseEntity<ResponseStructure<User>> addUser(@RequestBody User user){
        User user1 = userService.addUser(user);
       return responseBuilder.created("User has been successfully created", user1);
    }

    @PostMapping("/users/{id}")
    public ResponseEntity<ResponseStructure<User>> findUserById(String id){
        User user = userService.findByUserId(id);
        return responseBuilder.ok("User has been found", user);
    }

}
