package com.il.usermodule.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.il.usermodule.dto.UserResponseDTO;
import com.il.usermodule.dto.UserTargetResponseDTO;
import com.il.usermodule.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Define endpoints for user operations here,
    // For example, to get all users:
    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }
    @GetMapping("/target")
    public UserTargetResponseDTO getTarget() {
        return userService.getTarget();
    }
}
