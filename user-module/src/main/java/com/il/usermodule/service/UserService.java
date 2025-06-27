package com.il.usermodule.service;

import org.springframework.stereotype.Service;

import com.il.usermodule.dto.UserTargetResponseDTO;
import com.il.usermodule.dto.UserResponseDTO;
import com.il.usermodule.mapper.UserMapperV2;
import com.il.usermodule.model.UserProfile;
import com.il.usermodule.model.UserTarget;
import com.il.usermodule.repository.UserTargetRepo;
import com.il.usermodule.repository.UserProfileRepo;
import user.UserRequest;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserProfileRepo userProfileRepo;
    private final UserTargetRepo userTargetRepo;
    private final UserMapperV2 userMapper;

    public UserService(UserProfileRepo userRepo, UserTargetRepo userTargetRepo, UserMapperV2 userMapper ) {
        this.userProfileRepo = userRepo;
        this.userTargetRepo = userTargetRepo;
        this.userMapper = userMapper;
    }
    /**
     * Get all users.
     *
     * @return List of UserResponseDTO containing user profile information
     */
    public List<UserResponseDTO> getAllUsers() {
        List<UserProfile> userProfiles = userProfileRepo.findAll();
        return userProfiles.stream().map(userMapper::toResDto).toList();
    }
    /**
     * Create a new user target.
     *
     * @param userId the user ID as a string
     * @return UserTargetResponseDTO containing the created user target information
     */
    public void createTarget(String userIdString) {
        UUID userId = UUID.fromString(userIdString);
        UserTarget userTarget = new UserTarget.Builder()
                .id(userId)
                .reading(0)
                .listening(0)
                .writing(0)
                .speaking(0)
                .nextExamDate(null)
                .build();
        UserTarget savedUserTarget = userTargetRepo.save(userTarget);
        // log the saved user target
        savedUserTarget.logger();
    }
    /**
     * Get user target by userId.
     *
     * @param userIdString the user ID as a string
     * @return UserTargetResponseDTO containing the user's target information
     */
    public UserTargetResponseDTO getTarget(String userIdString) {
        UUID userId = UUID.fromString(userIdString);
        UserTarget userTarget = userTargetRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        userTarget.logger();
        return userMapper.toResto(userTarget);
    }
    /**
     * Create a new user profile.
     *
     * @param user the UserRequestDTO containing user profile information
     * @return UserResponseDTO containing the created user profile information
     */
    public UserResponseDTO createUser(UserRequest userRequest) {
        // log the new user profile creation
        try {
            UserProfile newUserProfile = UserProfile.builder()
                    .first_name(userRequest.getFirstName())
                    .last_name(userRequest.getLastName())
                    .avatar(userRequest.getAvatar())
                    .is_email_notification(userRequest.getIsEmailNotification())
                    .vocab_usage_count(userRequest.getVocabUsageCount())
                    .build();

            UserProfile savedUser = userProfileRepo.save(newUserProfile);
            // log the saved user profile
            savedUser.logger();
            createTarget(savedUser.getId().toString());
            return userMapper.toResDto(savedUser);
        }
        catch (Exception e) {
            System.err.println("Error creating user profile: " + e.getMessage());
            throw new RuntimeException("Failed to create user profile", e);
        }
    }
}