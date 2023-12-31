package com.roman.forum.controller;

import com.roman.forum.errors.ContentDoesNotExistException;
import com.roman.forum.model.Message;
import com.roman.forum.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topics")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping(path = "/{topicId}/messages")
    public ResponseEntity<List<Message>> getAllMessagesByTopicId(@PathVariable(value = "topicId") Long id){
        try{
            List<Message> messages = messageService.getAllMessagesByTopicId(id);
            return ResponseEntity.ok(messages);
        } catch (ContentDoesNotExistException e){
            return ResponseEntity.notFound().build();
        }
    }



    @DeleteMapping(path = "/{topicId}/messages/{messageId}")
    public ResponseEntity<Message> deleteMessageById(@PathVariable(value = "messageId") Long messageId){
        messageService.deleteMessageById(messageId);
        return ResponseEntity.noContent().build();
    }


    @PostMapping(path = "/{topicId}/messages")
    public ResponseEntity<Message> saveMessageWithTopicId(@PathVariable(value = "topicId") Long id, @RequestBody Message message){
        try {
            Message newMessage = messageService.saveMessageWithTopicId(id, message);
            return ResponseEntity.created(URI.create("my-url.com/topics/%s/messages/%s".formatted(id, newMessage.getId()))).body(newMessage);
        }catch (ContentDoesNotExistException e){
            return ResponseEntity.notFound().build();
        }
    }

}
