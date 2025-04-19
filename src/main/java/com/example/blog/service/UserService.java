package com.example.blog.service;

import com.example.blog.entity.User;

public interface UserService {
    User addUser(User user);

    User findByUserId(String id);
}
