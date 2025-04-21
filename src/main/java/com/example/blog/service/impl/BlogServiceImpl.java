package com.example.blog.service.impl;

import com.example.blog.dto.PaginatedResponseDTO;
import com.example.blog.entity.Blog;
import com.example.blog.entity.User;
import com.example.blog.exceptions.BlogNotFoundByIdException;
import com.example.blog.exceptions.BlogsNotFoundException;
import com.example.blog.repository.BlogRepository;
import com.example.blog.repository.UserRepository;
import com.example.blog.service.BlogService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
    private final UserRepository userRepository;

    @Override
    public Blog addBlog(Blog blog, String email) {
        User user = userRepository.findByEmail(email);
        blog.setUser(user);
        return blogRepository.save(blog);
    }

    @Override
    public Blog findBlogById(String id) {
        Optional<Blog> optional = blogRepository.findById(id);
        if(optional.isEmpty()){
            throw new BlogNotFoundByIdException("Blog Id Not Found");
        }
        return optional.get();
    }

    @Override
    public Blog deleteBlogById(String id) {
       Optional<Blog> optional = blogRepository.findById(id);
        if(optional.isEmpty()){
            throw new BlogNotFoundByIdException("Blog Id Not Found");
        }
        blogRepository.deleteById(id);
        return optional.get();
    }

    @Override
    public Blog updateBlogById(String id, Blog blog) {
        Blog fetchedblog = blogRepository.findById(id).get();
        if(fetchedblog == null){
            throw new BlogNotFoundByIdException("Blog Id Not Found");
        }
       fetchedblog.setTitle(blog.getTitle());
        fetchedblog.setDescription(blog.getDescription());

       return blogRepository.save(fetchedblog);

    }

    @Override
    public PaginatedResponseDTO<List<Blog>> findBlogs(int page, int size) {

        Pageable pageable = (Pageable) PageRequest.of(page, size);
        Page<Blog> pages = blogRepository.findAll((org.springframework.data.domain.Pageable) pageable);

        if(pages.isEmpty()){
            throw new BlogsNotFoundException("No Blogs has been found");
        }

        PaginatedResponseDTO<List<Blog>> responseDTO = new PaginatedResponseDTO<>();
        responseDTO.setTotalPages(pages.getTotalPages());
        responseDTO.setData(pages.getContent());
        responseDTO.setPageSize(pages.getSize());

        return responseDTO;
    }

    @Override
    public PaginatedResponseDTO<List<Blog>> findAllByUserEmail(String email, int page, int size) {

        Pageable object = (Pageable) PageRequest.of(page, size);
        Page<Blog> pages = blogRepository.findAllByUserEmail(email,object);

        if (pages.isEmpty()){
            throw new BlogsNotFoundException("No Blogs Has Been Found");
        }

        PaginatedResponseDTO<List<Blog>> responseDTO = new PaginatedResponseDTO<>();
        responseDTO.setTotalPages(pages.getTotalPages());
        responseDTO.setData(pages.getContent());
        responseDTO.setPageSize(pages.getSize());

        return responseDTO;

    }


}
