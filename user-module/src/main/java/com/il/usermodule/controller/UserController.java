package com.il.usermodule.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.il.usermodule.dto.UserResponseDTO;
import com.il.usermodule.dto.UserTargetResponseDTO;
import com.il.usermodule.service.UserService;

import java.util.List;

@RestController
@Tag(name = "User API", description = "APIs for user management")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Define endpoints for user operations here,
    // For example, to get all users:
    @GetMapping
    @Operation(summary = "Get all users")
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/target")
    @Operation(summary = "Get user target by userId")
    public UserTargetResponseDTO getTarget(@RequestHeader("userId") String userId) {
        return userService.getTarget(userId);
    }
}
