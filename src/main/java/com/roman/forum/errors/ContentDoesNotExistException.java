package com.roman.forum.errors;

import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.UUID;


public class ContentDoesNotExistException extends RuntimeException{

    public ContentDoesNotExistException(UUID id, String contentType) {
        super("A %s with id '%s' does not exist".formatted(contentType, id));
    }
}
