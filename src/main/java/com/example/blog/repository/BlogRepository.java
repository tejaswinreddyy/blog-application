package com.example.blog.repository;

import com.example.blog.dto.PaginatedResponseDTO;
import com.example.blog.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, String> {

    Page<Blog> findAllByUserEmail(String email, Pageable object);

}
