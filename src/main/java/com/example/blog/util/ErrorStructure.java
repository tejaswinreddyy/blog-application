package com.example.blog.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class ErrorStructure{

    private int statusCode;
    @JsonProperty(namespace = "error_message")
    private String errorMessage;
}
