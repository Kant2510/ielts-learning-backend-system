package com.il.authmodule.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.il.authmodule.config.RSAKeyConfig;

import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

@RestController
@RequestMapping("/.well-known")
@Tag(name = "JWK API", description = "APIs for JWK")
public class JWKController {
    private final RSAKeyConfig rsaKeyConfig;

    public JWKController(RSAKeyConfig rsaKeyConfig) {
        this.rsaKeyConfig = rsaKeyConfig;
    }

    @GetMapping("/public-key")
    @Operation(summary = "Get public key")
    public ResponseEntity<String> getPublicKey() {
        RSAPublicKey publicKey = rsaKeyConfig.getPublicKey();
        byte[] encoded = publicKey.getEncoded();
        return ResponseEntity.ok(Base64.getEncoder().encodeToString(encoded));
    }
}
