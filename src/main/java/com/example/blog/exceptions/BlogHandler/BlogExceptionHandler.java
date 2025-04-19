package com.example.blog.exceptions.BlogHandler;

import com.example.blog.exceptions.BlogNotFoundByIdException;
import com.example.blog.util.ErrorStructure;
import com.example.blog.util.ResponseBuilderFactory;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@AllArgsConstructor
public class BlogExceptionHandler {

    private final ResponseBuilderFactory responseStructure;

    @ExceptionHandler
    public ResponseEntity<ErrorStructure> handleBlogNotFoundByIdException(BlogNotFoundByIdException ex){
        return responseStructure.respondWith(HttpStatus.BAD_REQUEST, "Blog Id Not Found in the database");
    }
}
