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
    public void tokenService_whenValidateResetPasswordToken_thenReturnsTrue(){

        String username = "username";
        String token = tokenService.generatePasswordResetToken(username);
        boolean result = tokenService.validatePasswordResetToken(token, username);

        Assertions.assertNotNull(token);
        Assertions.assertTrue(result);
    }

}
