package com.roman.forum.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ContentDoesNotExistAdvice {

    @ResponseBody
    @ExceptionHandler(ContentDoesNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String contentDoesNotExistHandler(ContentDoesNotExistException ex){
        return ex.getMessage();
    }
}
