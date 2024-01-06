package com.roman.forum.mapper;

import com.roman.forum.model.DTO.TopicDisplayDTO;
import com.roman.forum.model.Topic;
import org.springframework.stereotype.Component;

@Component
public class TopicMapper {

    public TopicDisplayDTO toDto(Topic topic){

        return new TopicDisplayDTO(
                topic.getTitle(),
                topic.getDescription(),
                topic.getImage(),
                topic.getMessages().size(),
                topic.getId(),
                topic.getLikes(),
                topic.getDislikes(),
                topic.getTimeCreated(),
                topic.getTimeUpdated());
    }
}
