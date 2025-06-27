package com.il.usermodule.dto;

public record UserResponseDTO(
    String id,
    String firstName,
    String lastName,
    String avatar,
    Boolean isEmailNotification,
    int vocabUsageCount
) {}