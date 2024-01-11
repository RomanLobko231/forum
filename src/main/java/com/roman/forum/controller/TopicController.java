package com.roman.forum.controller;

import com.roman.forum.errors.ContentDoesNotExistException;
import com.roman.forum.model.DTO.TopicDisplayDTO;
import com.roman.forum.model.Image;
import com.roman.forum.model.Topic;
import com.roman.forum.service.ImageService;
import com.roman.forum.service.TopicService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/topics")
public class TopicController {

    private final TopicService topicService;
    private final ImageService imageService;

    public TopicController(TopicService topicService, ImageService imageService) {
        this.topicService = topicService;
        this.imageService = imageService;
    }

    @GetMapping
    public List<TopicDisplayDTO> getAllTopics(){
        return topicService.getAllTopics();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Topic> getTopicById(@PathVariable(name = "id", required = false)  UUID topicId){
        Topic fetchedTopic = topicService.getTopicById(topicId).orElseThrow(() -> new ContentDoesNotExistException(topicId, "topic"));
        return ResponseEntity.ok().body(fetchedTopic);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Topic> saveTopic(@RequestPart(name = "images", required = false) List<MultipartFile> files, @RequestPart(name = "topic") Topic topic){

        if (files != null){
            List<Image> images = new ArrayList<>();
            for (MultipartFile file: files){
                try {
                    images.add(new Image(file.getBytes(), file.getOriginalFilename(), file.getSize()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            images.forEach(image -> System.out.println(image.getImageName()));
            imageService.saveImages(images);
            topic.setImages(images);
        }
        Topic createdTopic = topicService.saveTopic(topic);
        return ResponseEntity.ok(createdTopic);
    }

    @PutMapping
    public ResponseEntity<Topic> updateTopic(@RequestBody Topic topic){
        try{
            Topic newTopic = topicService.updateTopic(topic);
            return ResponseEntity.accepted().body(newTopic);
        }
        catch (ContentDoesNotExistException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Topic> deleteTopicById(@PathVariable(name = "id") UUID topicId){
        try{
            topicService.deleteTopicById(topicId);
            return ResponseEntity.noContent().build();
        }
        catch (ContentDoesNotExistException e){
            return ResponseEntity.notFound().build();
        }
    }
}
