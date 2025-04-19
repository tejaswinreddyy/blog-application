package com.example.blog.controller;

import com.example.blog.entity.Blog;
import com.example.blog.service.BlogService;
import com.example.blog.util.ResponseBuilderFactory;
import com.example.blog.util.ResponseStructure;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class BlogController {

    private final BlogService blogService;
    private final ResponseBuilderFactory resposeBuilder;

    @PostMapping("/blogs")
    public ResponseEntity<ResponseStructure<Blog>> addBlog(@RequestBody Blog blog,  String email){
        Blog blog1 = blogService.addBlog(blog,email);
        return resposeBuilder.created("Blog has been succesfully created", blog1);
    }

    @GetMapping("/blogs/{id}")
    public ResponseEntity<ResponseStructure<Blog>> findBlogById(@PathVariable String id){
        Blog blog = blogService.findBlogById(id);
        return resposeBuilder.ok("Blog has been successfully fetched", blog);
    }

    @DeleteMapping("/")

}
