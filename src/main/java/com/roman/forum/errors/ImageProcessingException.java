package com.roman.forum.errors;

import java.io.IOException;

public class ImageProcessingException extends RuntimeException {

    public ImageProcessingException(IOException e, String imageName) {
        super("An error occurred while processing an image '%s'. Error: %s".formatted(imageName, e.getMessage()));
    }
}
