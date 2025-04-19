package com.example.blog.service.impl;

import com.example.blog.entity.User;
import com.example.blog.exceptions.UserExistByEmailException;
import com.example.blog.repository.BlogRepository;
import com.example.blog.service.BlogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;

    @Override
    public User addUser(User user) {
        if(blogRepository.existsByEmail(user.getEmail())){
            log.error("Failed to register user. user already exists with the requested email.");
            throw new UserExistByEmailException("Email has already been registered  ");
        }
        return blogRepository.save(user);
    }
}
