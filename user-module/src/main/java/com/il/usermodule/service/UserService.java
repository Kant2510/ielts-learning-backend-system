package com.il.usermodule.service;

import org.springframework.stereotype.Service;

import com.il.usermodule.dto.UserTargetResponseDTO;
import com.il.usermodule.dto.UserRequestDTO;
import com.il.usermodule.dto.UserResponseDTO;
import com.il.usermodule.mapper.UserMapperV2;
import com.il.usermodule.model.UserProfile;
import com.il.usermodule.model.UserTarget;
import com.il.usermodule.repository.UserTargetRepo;
import com.il.usermodule.repository.UserProfileRepo;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserProfileRepo userProfileRepo;
    private final UserTargetRepo userTargetRepo;
    private final UserMapperV2 userMapper;

    public UserService(UserProfileRepo userRepo, UserTargetRepo userTargetRepo, UserMapperV2 userMapper) {
        this.userProfileRepo = userRepo;
        this.userTargetRepo = userTargetRepo;
        this.userMapper = userMapper;
    }

    // Add methods to interact with the UserRepo here,
    // For example, to get all users, find by ID, etc.
    public List<UserResponseDTO> getAllUsers() {
        List<UserProfile> userProfiles = userProfileRepo.findAll();
        return userProfiles.stream().map(userMapper::toResDto).toList();
    }

    public UserTargetResponseDTO getTarget() {
        UUID test_id = UUID.fromString("00000000-0000-0000-0000-000000000000");
        UserTarget userTarget = userTargetRepo.findById(test_id).orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toResto(userTarget);
    }

    public UserResponseDTO createUser(UserRequestDTO user) {
        // log the new user profile creation
        UserProfile userProfile = userMapper.toEntity(user);
        System.out.println("Mapped user profile: ");
        System.out.println("User ID: " + userProfile.getId());
        System.out.println("First Name: " + userProfile.getFirstName());
        System.out.println("Last Name: " + userProfile.getLastName());
        System.out.println("Avatar: " + userProfile.getAvatar());
        System.out.println("Is Email Notification: " + userProfile.getIsEmailNotification());
        System.out.println("Vocab Usage Count: " + userProfile.getVocabUsageCount());
        UserProfile savedUser = userProfileRepo.save(userProfile);
        return userMapper.toResDto(savedUser);
    }
}