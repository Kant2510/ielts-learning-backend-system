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
        System.out.println("Registering new user: ");
        System.out.println("Email: " + request.getEmail());
        System.out.println("Password: " + request.getPassword());
        System.out.println("First Name: " + request.getFirstName());
        System.out.println("Last Name: " + request.getLastName());
        System.out.println("Role: " + request.getRole());


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
        // log the new auth creation
        System.out.println("Creating new auth object: ");
        System.out.println("Email: " + newAuth.getEmail());
        System.out.println("Provider: " + newAuth.getProvider());
        System.out.println("Role: " + newAuth.getRole());
        System.out.println("Is Banned: " + newAuth.getIsBanned());

//        if (newAuth == null) {
//            throw new RuntimeException("Failed to create Auth object");
//        }

        UserProfile newUserProfile = new UserProfile.Builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .avatar("") // Default avatar URL
                .emailNotification(false)
                .vocabUsageCount(0)
                .build();
        // log the new user profile creation
        System.out.println("Creating new user profile: ");
        System.out.println("First Name: " + newUserProfile.getFirstName());
        System.out.println("Last Name: " + newUserProfile.getLastName());
        System.out.println("Avatar: " + newUserProfile.getAvatar());
        System.out.println("Is Email Notification: " + newUserProfile.getIsEmailNotification());
        System.out.println("Vocab Usage Count: " + newUserProfile.getVocabUsageCount());
        // Check if the user profile was created successfully

//        if (newUserProfile == null) {
//            throw new RuntimeException("Failed to create User object");
//        }

        UserResponse createdUser = userGRPCClientService.createUser(newUserProfile);
        if (createdUser == null) {
            throw new RuntimeException("Failed to create user in gRPC service");
        }

        newUserProfile.BuildMore().id(createdUser.getId());
        // log the user profile ID after creation
        System.out.println("User Profile ID after creation: " + newUserProfile.getId());
        System.out.println("User Profile First Name after creation: " + newUserProfile.getFirstName());
        System.out.println("User Profile Last Name after creation: " + newUserProfile.getLastName());
        System.out.println("User Profile Avatar after creation: " + newUserProfile.getAvatar());
        System.out.println("User Profile Is Email Notification after creation: " + newUserProfile.getIsEmailNotification());
        System.out.println("User Profile Vocab Usage Count after creation: " + newUserProfile.getVocabUsageCount());

        newAuth.BuildMore().userProfile(newUserProfile);
        // log the new auth object with user profile
        System.out.println("New Auth object with User Profile: ");
        System.out.println("Email: " + newAuth.getEmail());
        System.out.println("Password: " + newAuth.getPassword());
        System.out.println("Provider: " + newAuth.getProvider());
        System.out.println("Role: " + newAuth.getRole());
        System.out.println("Is Banned: " + newAuth.getIsBanned());
        System.out.println("User Profile ID: " + newAuth.getUserProfile().getId());
        System.out.println("User Profile First Name: " + newAuth.getUserProfile().getFirstName());
        System.out.println("User Profile Last Name: " + newAuth.getUserProfile().getLastName());
        System.out.println("User Profile Avatar: " + newAuth.getUserProfile().getAvatar());
        System.out.println("User Profile Is Email Notification: " + newAuth.getUserProfile().getIsEmailNotification());
        System.out.println("User Profile Vocab Usage Count: " + newAuth.getUserProfile().getVocabUsageCount());

        Auth savedAuth = authRepo.save(newAuth);

        return getRegisterResponseDTO(savedAuth);
    }

    private static RegisterResponseDTO getRegisterResponseDTO(Auth savedAuth) {
        RegisterResponseDTO response = new RegisterResponseDTO();
        response.setId(savedAuth.getUserProfile().getId());
        response.setEmail(savedAuth.getEmail());
        response.setFirstName(savedAuth.getUserProfile().getFirstName());
        response.setLastName(savedAuth.getUserProfile().getLastName());
        response.setAvatar(savedAuth.getUserProfile().getAvatar());
        response.setIsEmailNotification(savedAuth.getUserProfile().getIsEmailNotification());
        response.setVocabUsageCount(savedAuth.getUserProfile().getVocabUsageCount());
        response.setProvider(savedAuth.getProvider());
        response.setIsBanned(savedAuth.getIsBanned());
        response.setRole(savedAuth.getRole().toString());
        return response;
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
