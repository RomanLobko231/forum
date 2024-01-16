package com.roman.forum.model.DTO;

import java.util.UUID;

public class LikeDislikeDTO {

    Integer likes;

    Integer dislikes;

    UUID id;

    public LikeDislikeDTO(Integer likes, Integer dislikes, UUID id) {
        this.likes = likes;
        this.dislikes = dislikes;
        this.id = id;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getDislikes() {
        return dislikes;
    }

    public void setDislikes(Integer dislikes) {
        this.dislikes = dislikes;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
