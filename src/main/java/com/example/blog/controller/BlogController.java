package com.example.blog.controller;

import com.example.blog.entity.User;
import com.example.blog.service.BlogService;
import com.example.blog.util.ResponseStructure;
import com.example.blog.util.ResponseBuilderFactory;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class BlogController {

    private final BlogService  blogService;
    private ResponseBuilderFactory responseBuilder;

    @PostMapping("/register")
    public ResponseEntity<ResponseStructure<User>> addUser(@RequestBody User user){
        User user1 = blogService.addUser(user);
       return responseBuilder.created("User has been successfully created", user1);
    }

}
