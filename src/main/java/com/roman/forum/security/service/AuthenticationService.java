package com.roman.forum.security.service;


import com.roman.forum.errors.ContentDoesNotExistException;
import com.roman.forum.errors.UserAlreadyExistsException;
import com.roman.forum.model.DTO.LoginResponseDTO;
import com.roman.forum.model.ForumUser;
import com.roman.forum.model.Role;
import com.roman.forum.repository.RolesRepository;
import com.roman.forum.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class AuthenticationService {

    private final UserRepository userRepository;

    private final RolesRepository rolesRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;


    public AuthenticationService(UserRepository userRepository, RolesRepository rolesRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }


    public ForumUser registerUser(String username, String password, String email){
        if (userRepository.existsUserByUsernameOrEmail(username, email)) throw new UserAlreadyExistsException(username, email);

        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = rolesRepository.findByAuthority("USER").orElseThrow(() -> new ContentDoesNotExistException("USER", "role"));
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);

        return userRepository.save(new ForumUser(null, username, encodedPassword, email, authorities));
    }

    public LoginResponseDTO loginUser(String username, String password){

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            String token = tokenService.generateToken(authentication);
            ForumUser user = userRepository.findByUsername(username).orElseThrow(() -> new ContentDoesNotExistException("username", "user"));

            return new LoginResponseDTO(user, token);
        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }
    }


}
