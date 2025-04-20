package com.example.blog.controller;

import com.example.blog.entity.User;
import com.example.blog.service.UserService;
import com.example.blog.util.ResponseStructure;
import com.example.blog.util.ResponseBuilderFactory;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private ResponseBuilderFactory responseBuilder;

    @PostMapping("/register")
    public ResponseEntity<ResponseStructure<User>> addUser(@RequestBody User user){
        User user1 = userService.addUser(user);
       return responseBuilder.created("User has been successfully created", user1);
    }

    @GetMapping("/account")
    public ResponseEntity<ResponseStructure<User>> findUserByEmail(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUserId(email);
        return responseBuilder.ok("User has been found", user);
    }

}
