package com.example.blog.service;

import com.example.blog.dto.PaginatedResponseDTO;
import com.example.blog.entity.Blog;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogService {

    Blog addBlog(Blog blog,String email);

    Blog findBlogById(String id);

    Blog deleteBlogById(String id);

    Blog updateBlogById(String id, Blog blog);

    PaginatedResponseDTO<List<Blog>> findBlogs (int page, int size);

    PaginatedResponseDTO<List<Blog>> findAllByUserEmail(String email, int page, int size);
}
