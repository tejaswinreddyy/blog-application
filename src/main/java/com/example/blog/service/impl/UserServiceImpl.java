package com.example.blog.service.impl;

import com.example.blog.entity.User;
import com.example.blog.exceptions.UserNotFoundByEmailException;
import com.example.blog.exceptions.UserNotFoundByIdException;
import com.example.blog.repository.UserRepository;
import com.example.blog.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User addUser(User user) {
        if(userRepository.existsByEmail(user.getEmail())){
            log.error("Failed to register user. user already exists with the requested email.");
            throw new UserNotFoundByEmailException("Email has already been registered  ");
        }
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        return userRepository.save(user);
    }

    @Override
    public User findByUserId(String email) {
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new UserNotFoundByIdException("User id not found");
        }
        return user;
    }
}
