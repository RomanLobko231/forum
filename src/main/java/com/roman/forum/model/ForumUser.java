package com.roman.forum.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
public class ForumUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "username", unique = true)
    @JsonProperty(value = "username")
    @NotBlank(message = "Username should not be empty")
    @Size(max = 20, message = "Username should not exceed 20 characters")
    private String username;

    @Column(name = "password")
    @JsonProperty(value = "password")
    @NotBlank(message = "Password should not be empty")
    @Size(max = 20, message = "Password should not exceed 20 characters")
    private String password;

    @Column(name = "email", unique = true)
    @JsonProperty(value = "email")
    @NotBlank(message = "Email should not be empty")
    @Size(max = 20, message = "Email should not exceed 20 characters")
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role_junction",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> authorities;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public ForumUser() {
        super();
        this.authorities = new HashSet<Role>();
    }

    public ForumUser(UUID id, String username, String password, String email, Set<Role> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }
}
