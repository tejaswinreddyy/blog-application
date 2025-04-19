package com.example.blog.util;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseStructure<T> {
    private int StatusCode;
    private String message;
    private T data;
}
