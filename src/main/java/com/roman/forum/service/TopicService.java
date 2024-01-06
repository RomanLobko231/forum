package com.roman.forum.service;

import com.roman.forum.errors.ContentDoesNotExistException;
import com.roman.forum.mapper.TopicMapper;
import com.roman.forum.model.DTO.TopicDisplayDTO;
import com.roman.forum.model.Topic;
import com.roman.forum.repository.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TopicService {

    private final TopicRepository topicRepository;
    private final TopicMapper mapper;

    public TopicService(TopicRepository topicRepository, TopicMapper mapper) {
        this.topicRepository = topicRepository;
        this.mapper = mapper;
    }

    public List<TopicDisplayDTO> getAllTopics(){
        return topicRepository
                .findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public Topic saveTopic(Topic topic){
        topic.setLikes(0);
        topic.setDislikes(0);
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
