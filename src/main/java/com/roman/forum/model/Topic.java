package com.roman.forum.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "topic")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    @JsonProperty(value = "title")
    @NotBlank(message = "Title should not be empty")
    @Size(max = 100, message = "Title should not exceed 100 characters")
    private String title;

    @Column(name = "description")
    @JsonProperty(value = "description")
    @Size(max = 500, message = "Title should not exceed 500 characters")
    private String description;

    @Column(name = "likes")
    @JsonProperty(value = "likes")
    private Integer likes;

    @Column(name = "dislikes")
    @JsonProperty(value = "dislikes")
    private Integer dislikes;

    @Lob
    @Column(name = "image")
    @JsonProperty(value = "image")
    private byte[] image;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
    private List<Message> messages;

    @CreationTimestamp(source = SourceType.DB)
    private Instant timeCreated;

    @UpdateTimestamp(source = SourceType.DB)
    private Instant timeUpdated;

    public List<Message> getMessages() {
        return messages;
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

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public Topic() {
    }

    public Topic(Long id, String title, String description, Integer likes, Integer dislikes, byte[] image, List<Message> messages) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.likes = likes;
        this.dislikes = dislikes;
        this.image = image;
        this.messages = messages;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

}
