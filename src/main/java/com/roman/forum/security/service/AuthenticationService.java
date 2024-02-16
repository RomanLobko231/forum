package com.roman.forum.security.service;


import com.roman.forum.errors.ContentDoesNotExistException;
import com.roman.forum.errors.UserAlreadyExistsException;
import com.roman.forum.model.DTO.LoginResponseDTO;
import com.roman.forum.model.ForumUser;
import com.roman.forum.model.Role;
import com.roman.forum.repository.RolesRepository;
import com.roman.forum.repository.UserRepository;
import com.roman.forum.service.MailService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional
public class AuthenticationService {

    private final UserRepository userRepository;

    private final RolesRepository rolesRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    private final MailService mailService;


    public AuthenticationService(UserRepository userRepository, RolesRepository rolesRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenService tokenService, MailService mailService) {
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.mailService = mailService;
    }


    public ForumUser registerUser(String username, String password, String email){
        if (userRepository.existsUserByUsernameOrEmail(username, email)) throw new UserAlreadyExistsException(username, email);

        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = rolesRepository.findByAuthority("USER").orElseThrow(() -> new ContentDoesNotExistException("USER", "role"));
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);

        ForumUser user = userRepository.save(new ForumUser(null, username, encodedPassword, email, authorities, false));
        user.setVerificationToken(UUID.randomUUID().toString());
        mailService.sendEmailVerification(user);

        return user;
    }

    public LoginResponseDTO loginUser(String username, String email, String password){

        String principal = username.isBlank() ? email : username;
        ForumUser user = userRepository
                .findByUsernameOrEmail(username, email)
                .orElseThrow(() -> new UsernameNotFoundException("User with principal '%s' was not found".formatted(principal)));

        if (!user.isEnabled()) throw new DisabledException("Account is not enabled, please verify provided email");

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(principal, password));
            String token = tokenService.generateToken(authentication);

            return new LoginResponseDTO(user, token);
        } catch (AuthenticationException e) {
            System.out.println(e.getMessage());
            throw new InternalAuthenticationServiceException(e.getMessage());
        }
    }

    public ForumUser verifyEmail(String token){
        ForumUser user = userRepository
                .findByVerificationToken(token)
                .orElseThrow(() -> new ContentDoesNotExistException(token, "verification token"));

        user.setVerificationToken(null);
        user.setEnabled(true);
        return userRepository.save(user);
    }
}
