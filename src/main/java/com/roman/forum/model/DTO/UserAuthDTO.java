package com.roman.forum.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;

public class UserAuthDTO {

    @JsonProperty(value = "password")
    @Size(min = 6, max = 40, message = "Password should be between 6 and 20 characters")
    private String password;

    @JsonProperty(value = "username")
    private String username;

    @JsonProperty(value = "email")
    private String email;

    public UserAuthDTO(String password, String username, String email) {
        this.password = password;
        this.username = username;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
