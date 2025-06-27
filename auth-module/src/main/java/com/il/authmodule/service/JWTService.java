package com.il.authmodule.service;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.il.authmodule.config.RSAKeyConfig;

import java.util.Date;
import java.util.UUID;

@Service
public class JWTService {
    // This service can be used to handle JWT operations such as token generation, validation, etc.
    // For now, it is a placeholder and can be expanded later as needed.
    private final RSAKeyConfig rsaKeyConfig;
    private final long expirationTime;

    public JWTService(RSAKeyConfig rsaKeyConfig,
                      @Value("${jwt.expiration.time}") long expirationTime) {
        this.rsaKeyConfig = rsaKeyConfig;
        this.expirationTime = expirationTime;
    }

    public String generateToken(UUID id, String email) {
        return Jwts.builder()
                .subject(String.valueOf(id))
                .claim("email", email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(rsaKeyConfig.getPrivateKey())
                .compact();
    }
}
