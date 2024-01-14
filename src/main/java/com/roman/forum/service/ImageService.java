package com.roman.forum.service;

import com.roman.forum.model.Image;
import com.roman.forum.repository.ImageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public void saveImages(List<Image> images){
        imageRepository.saveAll(images);
    }
}
