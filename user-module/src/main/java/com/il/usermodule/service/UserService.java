package com.il.usermodule.service;

import org.springframework.stereotype.Service;

import com.il.usermodule.dto.UserTargetResponseDTO;
import com.il.usermodule.dto.UserResponseDTO;
import com.il.usermodule.mapper.UserMapper;
import com.il.usermodule.model.User;
import com.il.usermodule.model.UserTarget;
import com.il.usermodule.repository.UserTargetRepo;
import com.il.usermodule.repository.UserRepo;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final UserTargetRepo userTargetRepo;

    public UserService(UserRepo userRepo, UserTargetRepo userTargetRepo) {
        this.userRepo = userRepo;
        this.userTargetRepo = userTargetRepo;
    }

    // Add methods to interact with the UserRepo here,
    // For example, to get all users, find by ID, etc.
    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepo.findAll();
        return users.stream().map(UserMapper::toDto).toList();
    }

    public UserTargetResponseDTO getTarget() {
        UUID test_id = UUID.fromString("00000000-0000-0000-0000-000000000000");
        UserTarget userTarget = userTargetRepo.findById(test_id).orElseThrow(() -> new RuntimeException("User not found"));
        return UserMapper.toDto(userTarget);
    }
}