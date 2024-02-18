package com.roman.forum.service;

import com.roman.forum.security.service.TokenService;
import com.roman.forum.utils.RsaKeyProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;

@SpringBootTest
public class TokenServiceTest {

    @Autowired
    TokenService tokenService;


    @Test
    public void whenValidateResetPasswordToken_shouldReturnsTrue(){

        String username = "username";
        String token = tokenService.generatePasswordResetToken(username);
        String usernameFromToken = tokenService.extractSubjectFromToken(token);
        boolean result = tokenService.validatePasswordResetToken(token);

        Assertions.assertEquals(username, usernameFromToken);
        Assertions.assertNotNull(token);
        Assertions.assertTrue(result);
    }

}
