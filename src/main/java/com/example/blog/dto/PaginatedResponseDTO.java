package com.example.blog.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Getter
@Setter
@Component
public class PaginatedResponseDTO <T extends Collection> {

    private int totalPages;
    private int pageSize;
    private T data;

}
