package com.roman.forum.model.DTO;

public class TopicDisplayDTO {

    private String title;

    private String description;

    private byte[] image;

    private int numberOfMessages;

    private Long id;

    private Integer likes;

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

    private Integer dislikes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TopicDisplayDTO(String title, String description, byte[] image, int numberOfMessages, Long id, Integer likes, Integer dislikes) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.numberOfMessages = numberOfMessages;
        this.likes = likes;
        this.dislikes = dislikes;
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
