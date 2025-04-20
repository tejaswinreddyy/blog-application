package com.example.blog.exceptions;

import lombok.Getter;

@Getter
public class BlogNotFoundByIdException extends RuntimeException {

  private String message;

    public BlogNotFoundByIdException(String message) {
        this.message = message;
    }
}
