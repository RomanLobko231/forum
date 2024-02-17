package com.roman.forum.security.service;

import com.roman.forum.utils.RsaKeyProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.security.SignatureException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class TokenService {


    private final JwtEncoder jwtEncoder;

    private final JwtDecoder jwtDecoder;

    private final RsaKeyProperties rsaKeyProperties;


    public TokenService(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder, RsaKeyProperties rsaKeyProperties) {
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
        this.rsaKeyProperties = rsaKeyProperties;
    }

    public String generatePasswordResetToken(String username) {
        Instant now = Instant.now();
        Instant expirationTime = now.plus(10, ChronoUnit.MINUTES);

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(expirationTime)
                .subject(username)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }


    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();
        Instant expirationTime = now.plus(1, ChronoUnit.MINUTES);

        String scope = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(expirationTime)
                .subject(authentication.getName())
                .claim("roles", scope)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public boolean validatePasswordResetToken(String token, String username) {
        try {
            Claims claims = extractClaims(token);

            return claims.getSubject().equals(username) && !claims.getExpiration().before(new Date());
        } catch (JwtException e) {
            return false;
        }
    }

    public String extractSubjectFromToken(String token) {
        return extractClaims(token).getSubject();
    }

    public Claims extractClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(rsaKeyProperties.getPublicKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
