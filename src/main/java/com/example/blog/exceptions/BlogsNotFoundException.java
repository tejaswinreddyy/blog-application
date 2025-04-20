package com.example.blog.exceptions;

import lombok.Getter;

@Getter
public class BlogsNotFoundException extends RuntimeException {

    private String message;

    public BlogsNotFoundException(String message) {
        this.message = message;
    }
}
