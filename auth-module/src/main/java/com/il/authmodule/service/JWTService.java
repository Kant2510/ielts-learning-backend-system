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

//    public boolean validateToken(String token) {
//        // Logic to validate the JWT token
//        try {
//            Jwts.parser()
//                .verifyWith((SecretKey) secretKey)
//                .build()
//                .parseSignedClaims(token);
//            return true;
//        } catch (SignatureException e) {
//            throw new JwtException("Invalid JWT signature");
//        } catch (JwtException e) {
//            throw new JwtException("Invalid JWT");
//        }
//    }
//
//    public UUID extractUserID(String token) {
//        String userID = Jwts.parser()
//                .verifyWith((SecretKey) secretKey)
//                .build()
//                .parseSignedClaims(token)
//                .getPayload()
//                .getSubject();
//        if (userID == null || userID.isEmpty()) {
//            throw new JwtException("Invalid JWT token: User ID not found");
//        }
//        try {
//            return UUID.fromString(userID);
//        } catch (IllegalArgumentException e) {
//            throw new JwtException("Invalid JWT token: User ID is not a valid UUID", e);
//        }
//    }
}
