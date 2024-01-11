package com.roman.forum.model.DTO;

import java.io.IOException;
import java.time.Instant;
import java.util.UUID;

public class TopicDisplayDTO {

    private String title;

    private String description;

    private byte[] image;

    private int numberOfMessages;

    private UUID id;

    private Integer likes;

    private Instant timeCreated;

    private Instant timeUpdated;

    private Integer dislikes;


    public Integer getLikes() {
        return likes;
    }

    public Instant getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Instant timeCreated) {
        this.timeCreated = timeCreated;
    }

    public Instant getTimeUpdated() {
        return timeUpdated;
    }

    public void setTimeUpdated(Instant timeUpdated) {
        this.timeUpdated = timeUpdated;
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

    public TopicDisplayDTO(String title, String description, byte[] image, int numberOfMessages, UUID id, Integer likes, Integer dislikes, Instant timeCreated, Instant timeUpdated) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.numberOfMessages = numberOfMessages;
        this.likes = likes;
        this.dislikes = dislikes;
        this.timeCreated = timeCreated;
        this.timeUpdated = timeUpdated;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getNumberOfMessages() {
        return numberOfMessages;
    }

    public void setNumberOfMessages(int numberOfMessages) {
        this.numberOfMessages = numberOfMessages;
    }

}
