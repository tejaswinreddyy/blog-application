package com.example.blog.service;

import com.example.blog.dto.PaginatedResponseDTO;
import com.example.blog.entity.Blog;

import java.util.List;

public interface BlogService {

    Blog addBlog(Blog blog,String email);

    Blog findBlogById(String id);

    Blog deleteBlogById(String id);

    Blog updateBlogById(String id, Blog blog);

    PaginatedResponseDTO findBlogs (int page, int size);
}
