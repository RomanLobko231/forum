package com.roman.forum.model.DTO;

import com.roman.forum.model.ForumUser;

public class LoginResponseDTO {

    private ForumUser user;

    private String jwt;

    public LoginResponseDTO(ForumUser user, String jwt) {
        this.user = user;
        this.jwt = jwt;
    }

    public ForumUser getUser() {
        return user;
    }

    public void setUser(ForumUser user) {
        this.user = user;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
