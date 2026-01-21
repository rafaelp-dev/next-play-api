package com.nextplay.nextplay.services.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.nextplay.nextplay.entities.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private Long jwtExpiration;

    public String generateToken (UserEntity userEntity) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);

            return JWT.create()
                    .withIssuer("nextplay")
                    .withSubject(userEntity.getEmail())
                    .withExpiresAt(getInstantNow().plusSeconds(jwtExpiration))
                    .sign(algorithm);
        }
        catch (JWTCreationException ex) {
            throw new RuntimeException("Erro ao gerar token: " + ex.getMessage());
        }
    }

    public String validateToken (String token) {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);

            return JWT.require(algorithm)
                    .withIssuer("nextplay")
                    .build()
                    .verify(token)
                    .getSubject();
    }

    private Instant getInstantNow () {
        return LocalDateTime.now().toInstant(ZoneOffset.of("-03:00"));
    }
}
