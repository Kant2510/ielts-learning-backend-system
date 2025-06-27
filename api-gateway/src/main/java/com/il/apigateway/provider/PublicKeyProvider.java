package com.il.apigateway.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
public class PublicKeyProvider {

    private RSAPublicKey cachedKey;
    @Value("${auth-module.jwk-endpoint}")
    private String publicKeyUrl;
    /**
     * Retrieves the RSA public key from the auth module.
     * The key is cached after the first retrieval to avoid multiple network calls.
     *
     * @return RSAPublicKey
     * @throws Exception if there is an error, retrieving or parsing the key
     */
    public RSAPublicKey getPublicKey() {
        try{
            if (cachedKey == null) {
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                // Call auth-module to get a public key
                RestTemplate restTemplate = new RestTemplate();
                String keyBase64 = restTemplate.getForObject(publicKeyUrl, String.class);
                byte[] keyBytes = Base64.getDecoder().decode(keyBase64);
                X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
                cachedKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);
                System.out.println("Get public key successfully: " + cachedKey.getModulus().toString(16));
            }
            return cachedKey;
        } catch (Exception e) {
            System.err.println("Error retrieving public key: " + e.getMessage());
            throw new RuntimeException("Failed to retrieve public key", e);
        }
    }
}

