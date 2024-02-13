package com.roman.forum.errors;

public class NullImageException extends NullPointerException{

    public NullImageException(String message) {
        super("One of the images that were sent might be null. Error: %s".formatted(message));
    }
}
