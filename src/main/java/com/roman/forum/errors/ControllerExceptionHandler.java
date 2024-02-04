package com.roman.forum.errors;

import com.roman.forum.model.ErrorMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Date;

@ControllerAdvice
@ResponseBody
public class ControllerExceptionHandler {

    private static final Logger logger = LogManager.getLogger(ControllerExceptionHandler.class);


    @ExceptionHandler(ContentDoesNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage contentDoesNotExistHandler(ContentDoesNotExistException ex, WebRequest request){
        logger.warn("Content was not found", ex);
        return new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage wrongArgumentTypeHandler(MethodArgumentTypeMismatchException ex, WebRequest request){
        logger.warn("A wrong argument was provided.", ex);
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage() + "\nPlease check that argument value corresponds with required type(UUID)",
                request.getDescription(false));
    }

    @ExceptionHandler(ImageProcessingException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage imageProcessingHandler(ImageProcessingException ex, WebRequest request){
        logger.warn("Error processing an image.", ex);
        return new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
    }

    @ExceptionHandler(NullImageException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage nullImageHandler(NullImageException ex, WebRequest request){
        logger.warn("Encountered a null file.", ex);
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorMessage userExistsHandler(UserAlreadyExistsException ex, WebRequest request){
        logger.info("Such user already exists", ex);
        return new ErrorMessage(
                HttpStatus.CONFLICT.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
    }
}
