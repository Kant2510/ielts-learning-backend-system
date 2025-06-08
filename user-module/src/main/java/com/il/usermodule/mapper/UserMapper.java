package com.il.usermodule.mapper;

import com.il.usermodule.dto.UserResponseDTO;
import com.il.usermodule.dto.UserTargetResponseDTO;
import com.il.usermodule.model.User;
import com.il.usermodule.model.UserTarget;

public class UserMapper {
    // This class can be used to map User entities to UserResponseDTOs and vice versa
    // For example, you can use MapStruct or manual mapping methods here

    // Example of a manual mapping method
    public static UserResponseDTO toDto(User users) {
        if (users == null) {
            return null;
        }

        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(users.getId().toString());
        dto.setFirst_name(users.getFirst_name());
        dto.setLast_name(users.getLast_name());
        dto.setEmail(users.getEmail());
        dto.setPassword(users.getPassword());
        dto.setAvatar(users.getAvatar());
        dto.setProvider(users.getProvider());
        dto.setEmail_notifications(users.getEmail_notifications());
        dto.setVocab_usage_count(users.getVocab_usage_count());
        dto.setIs_banned(users.getIs_banned());
        dto.setRole(users.getRole().toString()); // Assuming the role is a UUID

        return dto;
    }

    public static UserTargetResponseDTO toDto(UserTarget userTarget) {
        if (userTarget == null) {
            return null;
        }

        UserTargetResponseDTO dto = new UserTargetResponseDTO();
        dto.setId(userTarget.getId().toString());
        dto.setReading(userTarget.getReading());
        dto.setListening(userTarget.getListening());
        dto.setWriting(userTarget.getWriting());
        dto.setSpeaking(userTarget.getSpeaking());
        dto.setDuration(userTarget.getDuration());
        dto.setNextExamDate(userTarget.getNextExamDate() != null ? userTarget.getNextExamDate().toString() : null);

        return dto;
    }
}