package com.il.usermodule.dto;

public record UserTargetResponseDTO(
    String id,
    float reading,
    float listening,
    float writing,
    float speaking,
    String nextExamDate
) {}
