package com.il.authmodule.dto;

public record RegisterResponseDTO(
    String id,
    String email,
    String firstName,
    String lastName,
    String avatar,
    String provider,
    String role,
    Boolean isEmailNotification,
    int vocabUsageCount,
    Boolean isBanned
) {}
