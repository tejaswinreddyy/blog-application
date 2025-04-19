package com.example.blog.exceptions;

public class UserNotFoundByIdException extends RuntimeException {

    private String message;

    public UserNotFoundByIdException(String message) {
        this.message = message;
    }
}
