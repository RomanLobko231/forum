package com.roman.forum.model.DTO;

import com.roman.forum.model.ForumUser;

public class LoginResponseDTO {

    private ForumUser user;

    private String token;

    public LoginResponseDTO(ForumUser user, String token) {
        this.user = user;
        this.token = token;
    }

    public ForumUser getUser() {
        return user;
    }

    public void setUser(ForumUser user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
