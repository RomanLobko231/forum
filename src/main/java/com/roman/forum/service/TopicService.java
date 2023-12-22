package com.roman.forum.service;

import com.roman.forum.errors.ContentDoesNotExistException;
import com.roman.forum.model.Topic;
import com.roman.forum.repository.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    private final TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public List<Topic> getAllTopics(){
        return topicRepository.findAll();
    }

    public Topic saveTopic(Topic topic){
        return topicRepository.save(topic);
    }

    public Topic updateTopic(Topic topic) throws ContentDoesNotExistException{
        if (!topicRepository.existsById(topic.getId())) throw new ContentDoesNotExistException(topic.getId(), "topic");

        return topicRepository.save(topic);
    }

    public void deleteTopicById(Long id) throws ContentDoesNotExistException{
        if (!topicRepository.existsById(id)) throw new ContentDoesNotExistException(id, "topic");
        topicRepository.deleteById(id);
    }

    public Optional<Topic> getTopicById(Long id){
        return topicRepository.findById(id);
    }
}
