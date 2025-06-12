package com.il.authmodule.service;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Service
public class JWTService {
    // This service can be used to handle JWT operations such as token generation, validation, etc.
    // For now, it is a placeholder and can be expanded later as needed.
    private final Key secretKey;
    private final long expirationTime;

    public JWTService(@Value("${jwt.secret}") String secret,
                      @Value("${jwt.expiration.time}") long expirationTime) {
        byte[] keyBytes = Base64.getDecoder()
                .decode(secret.getBytes(StandardCharsets.UTF_8));
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
        this.expirationTime = expirationTime;
    }

    public String generateToken(UUID id, String email) {
        return Jwts.builder()
                .subject(String.valueOf(id))
                .claim("email", email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(secretKey)
                .compact();
    }

    public boolean validateToken(String token) {
        // Logic to validate the JWT token
        try {
            Jwts.parser()
                .verifyWith((SecretKey) secretKey)
                .build()
                .parseSignedClaims(token);
            return true;
        } catch (SignatureException e) {
            throw new JwtException("Invalid JWT signature");
        } catch (JwtException e) {
            throw new JwtException("Invalid JWT");
        }
    }

    public UUID extractUserID(String token) {
        String userID = Jwts.parser()
                .verifyWith((SecretKey) secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
        if (userID == null || userID.isEmpty()) {
            throw new JwtException("Invalid JWT token: User ID not found");
        }
        try {
            return UUID.fromString(userID);
        } catch (IllegalArgumentException e) {
            throw new JwtException("Invalid JWT token: User ID is not a valid UUID", e);
        }
    }
}
