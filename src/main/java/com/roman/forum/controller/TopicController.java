package com.roman.forum.controller;

import com.roman.forum.errors.ContentDoesNotExistException;
import com.roman.forum.model.Topic;
import com.roman.forum.service.TopicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/topics")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping
    public List<Topic> getAllTopics(){
        return topicService.getAllTopics();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Topic> getTopicById(@PathVariable(name = "id", required = false)  Long topicId){
        Topic fetchedTopic = topicService.getTopicById(topicId).orElseThrow(() -> new ContentDoesNotExistException(topicId, "topic"));
        return ResponseEntity.ok().body(fetchedTopic);
    }

    @PostMapping
    public ResponseEntity<Topic> saveTopic(@RequestBody Topic topic){
        Topic createdTopic = topicService.saveTopic(topic);
        return ResponseEntity.created(URI.create("my-url.com/" + createdTopic.getId())).body(createdTopic);
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
    public ResponseEntity<Topic> deleteTopicById(@PathVariable(name = "id") Long topicId){
        try{
            topicService.deleteTopicById(topicId);
            return ResponseEntity.noContent().build();
        }
        catch (ContentDoesNotExistException e){
            return ResponseEntity.notFound().build();
        }
    }
}
