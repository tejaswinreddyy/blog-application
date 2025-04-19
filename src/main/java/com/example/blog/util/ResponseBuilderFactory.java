package com.example.blog.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseBuilderFactory {


    public <T> ResponseEntity<ResponseStructure<T>> ok(String message, T data) {
        return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.<T>builder()
                .StatusCode(HttpStatus.OK.value())
                .message(message)
                .data(data)
                .build());
    }

    public <T> ResponseEntity<ResponseStructure<T>> created( String message, T data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseStructure.<T>builder()
                .StatusCode(HttpStatus.CREATED.value())
                .message(message)
                .data(data)
                .build());
    }

    public ResponseEntity<ErrorStructure> respondWith(HttpStatus statusCode, String message) {
        return ResponseEntity.status(statusCode).body(ErrorStructure.builder()
                .statusCode(statusCode.value())
                .errorMessage(message)
                .build());
    }
}
