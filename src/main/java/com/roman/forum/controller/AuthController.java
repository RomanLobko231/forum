package com.roman.forum.controller;

import com.roman.forum.model.DTO.LoginRequestDTO;
import com.roman.forum.model.DTO.LoginResponseDTO;
import com.roman.forum.model.DTO.UserAuthDTO;
import com.roman.forum.model.ForumUser;
import com.roman.forum.security.service.AuthenticationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = LogManager.getLogger(AuthController.class);

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(path = "/register")
    public ForumUser register(@RequestBody UserAuthDTO userInfo) {
        return authenticationService.registerUser(userInfo.getUsername(), userInfo.getPassword(), userInfo.getEmail());
    }

    @PostMapping(path = "/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO userInfo) {
        return authenticationService.loginUser(userInfo.getUsername(), userInfo.getPassword());
    }
}
