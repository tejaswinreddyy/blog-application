package com.example.blog.exceptions.UserHandler;


import com.example.blog.exceptions.UserExistByEmailException;
import com.example.blog.exceptions.UserNotFoundByEmailException;
import com.example.blog.exceptions.UserNotFoundByIdException;
import com.example.blog.util.ErrorStructure;
import com.example.blog.util.ResponseBuilderFactory;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@AllArgsConstructor
public class UserExceptionHandler {

    private final ResponseBuilderFactory responseBuilder;

    @ExceptionHandler
    public ResponseEntity<ErrorStructure> handleUserExistByEmailException(UserExistByEmailException ex){
        return responseBuilder.respondWith(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorStructure> handleUserNotFoundByEmailException(UserNotFoundByEmailException ex){
        return responseBuilder.respondWith(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    public ResponseEntity<ErrorStructure> handleUserNotFoundByIdException(UserNotFoundByIdException ex){
        return responseBuilder.respondWith(HttpStatus.NOT_FOUND, ex.getMessage());
    }
}
