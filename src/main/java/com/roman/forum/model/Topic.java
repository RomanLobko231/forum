package com.roman.forum.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "topic")
public class Topic extends Auditable{

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


    public Topic() {
    }

    public Topic(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
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
