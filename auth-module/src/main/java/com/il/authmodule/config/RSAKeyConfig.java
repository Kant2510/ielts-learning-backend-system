package com.il.authmodule.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Configuration
public class RSAKeyConfig {
    @Value("${rsa.private.key}")
    private String rawPrivateKey;

    @Value("${rsa.public.key}")
    private String rawPublicKey;

    private final KeyFactory keyFactory;

    public RSAKeyConfig() {
        try {
            this.keyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to initialize RSA KeyFactory", e);
        }
    }

    private PrivateKey convertStringToRSAPrivateKey(String rawKey) {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(rawKey);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
            return keyFactory.generatePrivate(keySpec);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException("Failed to convert raw private key to RSAPrivateKey", e);
        }
    }

    private PublicKey convertStringToRSAPublicKey(String rawKey) {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(rawKey);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            return keyFactory.generatePublic(keySpec);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException("Failed to convert raw public key to RSAPublicKey", e);
        }
    }

    public RSAPrivateKey getPrivateKey() {
        return (RSAPrivateKey) convertStringToRSAPrivateKey(rawPrivateKey);
    }

    public RSAPublicKey getPublicKey() {
        return (RSAPublicKey) convertStringToRSAPublicKey(rawPublicKey);
    }
}
