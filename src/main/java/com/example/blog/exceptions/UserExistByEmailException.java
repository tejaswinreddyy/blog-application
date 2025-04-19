package com.example.blog.exceptions;

import lombok.Getter;

@Getter
public class UserExistByEmailException extends RuntimeException {

    private String message;

    public UserExistByEmailException(String message) {
        this.message = message;
    }
}
