package com.roman.forum.errors;

public class UserAlreadyExistsException extends RuntimeException{

    public UserAlreadyExistsException(String username, String email) {
        super("A user with username '%s' or email '%s' already exists".formatted(username, email));
    }
}
