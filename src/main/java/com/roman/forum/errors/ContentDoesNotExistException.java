package com.roman.forum.errors;

import org.springframework.web.bind.annotation.ControllerAdvice;


public class ContentDoesNotExistException extends RuntimeException{

    public ContentDoesNotExistException(Long id, String contentType) {
        super("A %s with id '%s' does not exist".formatted(contentType, id));
    }
}
