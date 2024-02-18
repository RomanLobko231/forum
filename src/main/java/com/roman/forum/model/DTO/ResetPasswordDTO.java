package com.roman.forum.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResetPasswordDTO {

    @JsonProperty(value = "newPassword")
    private String newPassword;

    @JsonProperty(value = "token")
    private String token;

    public ResetPasswordDTO(String newPassword, String token) {
        this.newPassword = newPassword;
        this.token = token;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
