package com.example.blog.exceptions;

import lombok.Getter;

@Getter
public class UserNotFoundByEmailException extends RuntimeException {

    private String message;

    public UserNotFoundByEmailException(String message) {
        this.message = message;
    }
}
