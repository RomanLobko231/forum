package com.roman.forum.controller;

import com.roman.forum.errors.ContentDoesNotExistException;
import com.roman.forum.model.Message;
import com.roman.forum.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/topics")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping(path = "/{topicId}/messages")
    public ResponseEntity<List<Message>> getAllMessagesByTopicId(@PathVariable(value = "topicId") UUID id){
        List<Message> messages = messageService.getAllMessagesByTopicId(id);
        return ResponseEntity.ok(messages);
    }



    @DeleteMapping(path = "/{topicId}/messages/{messageId}")
    public ResponseEntity<Message> deleteMessageById(@PathVariable(value = "messageId") UUID messageId){
        messageService.deleteMessageById(messageId);
        return ResponseEntity.noContent().build();
    }


    @PostMapping(path = "/{topicId}/messages")
    public ResponseEntity<Message> saveMessageWithTopicId(@PathVariable(value = "topicId") UUID id, @RequestBody Message message){
        Message newMessage = messageService.saveMessageWithTopicId(id, message);
        return ResponseEntity.created(URI.create("my-url.com/topics/%s/messages/%s".formatted(id, newMessage.getId()))).body(newMessage);
    }

}
