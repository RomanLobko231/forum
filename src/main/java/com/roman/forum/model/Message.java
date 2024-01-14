package com.roman.forum.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "message", columnDefinition = "TEXT")
    @JsonProperty(value = "message")
    @NotBlank(message = "Message should not be empty")
    private String message;

    @CreationTimestamp(source = SourceType.DB)
    private Instant timeCreated;
    @UpdateTimestamp(source = SourceType.DB)
    private Instant timeUpdated;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "topic_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Topic topic;

    protected Message() {
    }

    public Message(UUID id, String message, Topic topic) {
        this.id = id;
        this.message = message;
        this.topic = topic;
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }


}
