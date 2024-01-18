package com.seals.camplanner.commons.config.security;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.seals.camplanner.user.models.User;

@Service
public class TokenService {

    public String generateToken(User user) {
        try {
            var algorithm = Algorithm.HMAC256("123456");
            return JWT.create()
                      .withSubject(user.getUsername())
                      .withIssuer("Camplanner API")
                      .withExpiresAt(LocalDateTime.now().plusHours(24).toInstant(ZoneOffset.of("+00:00")))
                      .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Token generation failed", exception);
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            var algorithm = Algorithm.HMAC256("123456");
            return JWT.require(algorithm)
                      .withIssuer("Camplanner API")
                      .build()
                      .verify(tokenJWT)
                      .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Invalid or expired token!");
        }
    }
}
