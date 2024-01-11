package com.roman.forum.service;

import com.roman.forum.errors.ContentDoesNotExistException;
import com.roman.forum.model.Message;
import com.roman.forum.repository.MessageRepository;
import com.roman.forum.repository.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    private final TopicRepository topicRepository;


    public MessageService(MessageRepository messageRepository, TopicRepository topicRepository) {
        this.messageRepository = messageRepository;
        this.topicRepository = topicRepository;
    }

    public List<Message> getAllMessagesByTopicId(UUID topicId) throws ContentDoesNotExistException{
        if (!topicRepository.existsById(topicId)) throw new ContentDoesNotExistException(topicId, "topic");

        return messageRepository.findByTopicId(topicId);
    }

    public void deleteMessageById(UUID messageId) throws ContentDoesNotExistException{
        if (!messageRepository.existsById(messageId)) throw new ContentDoesNotExistException(messageId, "message");
        messageRepository.deleteById(messageId);
    }

    public Message saveMessageWithTopicId(UUID topicId, Message message){
        if (!topicRepository.existsById(topicId)) throw new ContentDoesNotExistException(topicId, "topic");

        return topicRepository.findById(topicId).map(topic -> {
            topic.getMessages().add(message);
            message.setTopic(topic);

            topicRepository.save(topic);
            return messageRepository.save(message);

        }).orElseThrow(() -> new ContentDoesNotExistException(topicId, "topic"));
    }
}
