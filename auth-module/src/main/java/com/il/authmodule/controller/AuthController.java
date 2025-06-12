package com.il.authmodule.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.il.authmodule.dto.LoginRequestDTO;
import com.il.authmodule.dto.LoginResponseDTO;
import com.il.authmodule.dto.RegisterRequestDTO;
import com.il.authmodule.dto.RegisterResponseDTO;
import com.il.authmodule.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterRequestDTO request) {
        if (request.getEmail() == null || request.getPassword() == null ||
            request.getFirstName() == null || request.getLastName() == null ||
            request.getRole() == null) {
            return ResponseEntity.badRequest().build();
        }
        RegisterResponseDTO registerResult = authService.register(request);
        return registerResult != null
                ? ResponseEntity.ok(registerResult)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        LoginResponseDTO authResult = authService.authenticate(request);

        return authResult != null
                ? ResponseEntity.ok(authResult)
                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
