package com.roman.forum.service;

import com.roman.forum.model.ForumUser;
import com.roman.forum.repository.UserRepository;
import com.roman.forum.security.service.AuthenticationService;
import com.roman.forum.security.service.TokenService;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@SpringBootTest
public class AuthenticationServiceTest {

    @InjectMocks
    AuthenticationService authenticationService;

    @Mock
    UserRepository userRepository;

    @Mock
    TokenService tokenService;

    @Mock
    PasswordEncoder passwordEncoder;

    @Test
    public void whenResetPassword_withValidToken_shouldUpdatePasswordSuccessfully(){

        String username = "username";
        String newPassword = "password";
        String oldPassword = "oldPassword";
        String resetToken = "resetToken";
        ForumUser mockUser = new ForumUser(null, username, oldPassword, "email@email.com", new HashSet<>(),false);

        Mockito.when(userRepository.findByUsername(username)).thenReturn(Optional.of(mockUser));
        Mockito.when(userRepository.save(any(ForumUser.class))).thenAnswer(i -> i.getArgument(0));
        Mockito.when(tokenService.generatePasswordResetToken(username)).thenReturn(resetToken);
        Mockito.when(tokenService.validatePasswordResetToken(resetToken)).thenReturn(true);
        Mockito.when(tokenService.extractSubjectFromToken(resetToken)).thenReturn(username);


        authenticationService.resetPassword(resetToken, newPassword);

        ForumUser updatedUser = userRepository.findByUsername(username).get();

        Mockito.verify(userRepository, times(2)).findByUsername(username);
        Mockito.verify(userRepository).save(any(ForumUser.class));
        Assertions.assertEquals(passwordEncoder.encode(newPassword), updatedUser.getPassword());

    }
}
