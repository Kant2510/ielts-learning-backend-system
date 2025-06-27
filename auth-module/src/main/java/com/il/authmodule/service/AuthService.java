package com.il.authmodule.service;

import com.il.authmodule.dto.RegisterResponseDTO;
import com.il.authmodule.model.UserProfile;
import com.il.authmodule.service.grpc.UserGRPCClientService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.il.authmodule.constant.AuthConstant;
import com.il.authmodule.dto.RegisterRequestDTO;
import com.il.authmodule.dto.LoginRequestDTO;
import com.il.authmodule.dto.LoginResponseDTO;
import com.il.authmodule.model.Auth;
import com.il.authmodule.repository.AuthRepo;
import user.UserResponse;

import java.util.Objects;
import java.util.UUID;

@Service
public class AuthService {
    private final AuthRepo authRepo;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final UserGRPCClientService userGRPCClientService;

    public AuthService(AuthRepo authRepository, PasswordEncoder passwordEncoder, JWTService jwtService, UserGRPCClientService userGRPCClientService) {
        this.authRepo = authRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.userGRPCClientService = userGRPCClientService;
    }

    public RegisterResponseDTO register(RegisterRequestDTO request) {

        if (authRepo.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        String hashedPassword = passwordEncoder.encode(request.getPassword());

        UUID roleId = Objects.equals(request.getRole(), AuthConstant.END_USER_ROLE)
                ? AuthConstant.END_USER_ROLE_UUID : null;
        if (roleId == null) {
            throw new RuntimeException("Invalid role specified");
        }

        Auth newAuth = new Auth.Builder()
                .email(request.getEmail())
                .password(hashedPassword)
                .provider(AuthConstant.PROVIDER_TYPE.local.name())
                .isBanned(false)
                .role(roleId)
                .build();

        UserProfile newUserProfile = new UserProfile.Builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .avatar("") // Default avatar URL
                .emailNotification(false)
                .vocabUsageCount(0)
                .build();

        UserResponse createdUser = userGRPCClientService.createUser(newUserProfile);
        if (createdUser == null) {
            throw new RuntimeException("Failed to create user in gRPC service");
        }

        newUserProfile.BuildMore().id(createdUser.getId());

        newAuth.BuildMore().userProfile(newUserProfile);

        Auth savedAuth = authRepo.save(newAuth);
        // log the new auth object with the user profile
        savedAuth.logger();

        return getRegisterResponseDTO(savedAuth);
    }

    private static RegisterResponseDTO getRegisterResponseDTO(Auth savedAuth) {
        return new RegisterResponseDTO(
            savedAuth.getUserProfile().getId().toString(),
            savedAuth.getEmail(),
            savedAuth.getUserProfile().getFirstName(),
            savedAuth.getUserProfile().getLastName(),
            savedAuth.getUserProfile().getAvatar(),
            savedAuth.getProvider(),
            savedAuth.getRole().toString(),
            savedAuth.getUserProfile().getIsEmailNotification(),
            savedAuth.getUserProfile().getVocabUsageCount(),
            savedAuth.getIsBanned()
        );
    }

    public LoginResponseDTO authenticate(LoginRequestDTO request) {
        Auth auth = authRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), auth.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtService.generateToken(auth.getUserProfile().getId(), auth.getEmail());

        return new LoginResponseDTO(token, "");
    }
}
