package com.roman.forum.mapper;

import com.roman.forum.model.DTO.TopicDisplayDTO;
import com.roman.forum.model.Image;
import com.roman.forum.model.Topic;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class TopicMapper {

    public TopicDisplayDTO toDto(Topic topic){

         byte[] image = topic.getImages().isEmpty() ? null : topic.getImages().get(0).getImageBytes();

        return new TopicDisplayDTO(
                topic.getTitle(),
                topic.getDescription(),
                image,
                topic.getMessages().size(),
                topic.getId(),
                topic.getLikes(),
                topic.getDislikes(),
                topic.getTimeCreated(),
                topic.getTimeUpdated());
    }
}
