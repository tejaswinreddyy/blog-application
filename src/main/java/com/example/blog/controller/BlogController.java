package com.example.blog.controller;

import com.example.blog.dto.PaginatedResponseDTO;
import com.example.blog.entity.Blog;
import com.example.blog.service.BlogService;
import com.example.blog.util.ResponseBuilderFactory;
import com.example.blog.util.ResponseStructure;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@AllArgsConstructor
public class BlogController {

    private final BlogService blogService;
    private final ResponseBuilderFactory resposeBuilder;


    @PostMapping("/blogs")
    public ResponseEntity<ResponseStructure<Blog>> addBlog(@RequestBody Blog blog) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Blog blog1 = blogService.addBlog(blog, email);
        return resposeBuilder.created("Blog has been succesfully created", blog1);
    }

    @GetMapping("/blogs/{id}")
    public ResponseEntity<ResponseStructure<Blog>> findBlogById(@PathVariable String id) {
        Blog blog = blogService.findBlogById(id);
        return resposeBuilder.ok("Blog has been successfully fetched", blog);
    }

    @DeleteMapping("/blogs/{id}")
    public ResponseEntity<ResponseStructure<Blog>> deleteBlogById(@PathVariable String id) {
        Blog blog = blogService.deleteBlogById(id);
        return resposeBuilder.ok("Blog has been successfully Deleted", blog);
    }

    @PutMapping("/blogs/{id}")
    public ResponseEntity<ResponseStructure<Blog>> updateBlogById(@PathVariable String id, @RequestBody Blog blog) {
        Blog blog1 = blogService.updateBlogById(id, blog);
        return resposeBuilder.ok("Blog has been successfully updated", blog1);
    }

    @GetMapping("/blogs")
    public ResponseEntity<ResponseStructure<PaginatedResponseDTO<List<Blog>>>> findBlogs(int page, int size) {
        PaginatedResponseDTO<List<Blog>> blogs = blogService.findBlogs(page, size);
        return resposeBuilder.ok("List of Blogs successfully fetched", blogs);
    }

    @GetMapping("/blogs/user-blogs")
    public ResponseEntity<ResponseStructure<PaginatedResponseDTO<List<Blog>>>> findAllBlogsByUserEmail(int page, int size){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        PaginatedResponseDTO<List<Blog>> blogs = blogService.findAllByUserEmail(email,page, size);
        return resposeBuilder.ok("List of Blogs of User successfully fetched", blogs);
    }
}
