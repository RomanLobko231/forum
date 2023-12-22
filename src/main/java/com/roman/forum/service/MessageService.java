package com.roman.forum.service;

import com.roman.forum.errors.ContentDoesNotExistException;
import com.roman.forum.model.Message;
import com.roman.forum.repository.MessageRepository;
import com.roman.forum.repository.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    private final TopicRepository topicRepository;


    public MessageService(MessageRepository messageRepository, TopicRepository topicRepository) {
        this.messageRepository = messageRepository;
        this.topicRepository = topicRepository;
    }

    public List<Message> getAllMessagesByTopicId(Long topicId) throws ContentDoesNotExistException{
        if (!topicRepository.existsById(topicId)) throw new ContentDoesNotExistException(topicId, "topic");

        return messageRepository.findByTopicId(topicId);
    }

    public void deleteMessageById(Long messageId) throws ContentDoesNotExistException{
        if (!messageRepository.existsById(messageId)) throw new ContentDoesNotExistException(messageId, "message");
        messageRepository.deleteById(messageId);
    }

    public Message saveMessageWithTopicId(Long topicId, Message message){
        if (!topicRepository.existsById(topicId)) throw new ContentDoesNotExistException(topicId, "topic");

        return topicRepository.findById(topicId).map(topic -> {
            message.setTopic(topic);
            return messageRepository.save(message);
        }).orElseThrow(() -> new ContentDoesNotExistException(topicId, "topic"));
    }
}
