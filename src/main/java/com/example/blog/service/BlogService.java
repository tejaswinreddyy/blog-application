package com.example.blog.service;

import com.example.blog.entity.Blog;

public interface BlogService {

    Blog addBlog(Blog blog,String email);

    Blog findBlogById(String id);

    Blog deleteBlogById(String id);

    Blog updateBlogById(String id, Blog blog);
}
