package com.roman.forum.controller;

import com.roman.forum.errors.ContentDoesNotExistException;
import com.roman.forum.model.DTO.LoginRequestDTO;
import com.roman.forum.model.DTO.LoginResponseDTO;
import com.roman.forum.model.DTO.UserAuthDTO;
import com.roman.forum.model.ForumUser;
import com.roman.forum.repository.UserRepository;
import com.roman.forum.security.service.AuthenticationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public ResponseEntity<ForumUser> register(@RequestBody UserAuthDTO userInfo) {
        ForumUser user = authenticationService.registerUser(userInfo.getUsername(), userInfo.getPassword(), userInfo.getEmail());
        return ResponseEntity.ok().body(user);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO userInfo) {
        LoginResponseDTO authUser = authenticationService.loginUser(userInfo.getUsername(), userInfo.getEmail(), userInfo.getPassword());
        return ResponseEntity.ok().body(authUser);
    }

    @PostMapping(path = "/verify")
    public ResponseEntity<ForumUser> verifyEmail(@RequestParam("token") String token) {
        ForumUser verifiedUser = authenticationService.verifyEmail(token);
        return ResponseEntity.ok(verifiedUser);

    }
}
