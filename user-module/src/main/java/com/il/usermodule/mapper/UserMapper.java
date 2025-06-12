package com.il.usermodule.mapper;

import com.il.usermodule.dto.UserRequestDTO;
import com.il.usermodule.dto.UserResponseDTO;
import com.il.usermodule.dto.UserTargetResponseDTO;
import com.il.usermodule.model.UserProfile;
import com.il.usermodule.model.UserTarget;

import java.util.UUID;

public class UserMapper {
    // This class can be used to map User entities to UserResponseDTOs and vice versa,
    // For example, you can use MapStruct or manual mapping methods here

    // Example of a manual mapping method
    public static UserResponseDTO toDto(UserProfile users) {
        if (users == null) {
            return null;
        }

        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(users.getId().toString());
        dto.setFirstName(users.getFirstName());
        dto.setLastName(users.getLastName());
        dto.setAvatar(users.getAvatar());
        dto.setEmailNotifications(users.getIsEmailNotification());
        dto.setVocabUsageCount(users.getVocabUsageCount());

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
//        dto.setDuration(userTarget.getDuration());
        dto.setNextExamDate(userTarget.getNextExamDate() != null ? userTarget.getNextExamDate().toString() : null);

        return dto;
    }

    public static UserProfile toEntity(UserRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        UserProfile userProfile = new UserProfile();
        userProfile.setFirstName(dto.getFirstName());
        userProfile.setLastName(dto.getLastName());
        userProfile.setAvatar(dto.getAvatar());
        userProfile.setIsEmailNotification(dto.getIsEmailNotification());
        userProfile.setVocabUsageCount(dto.getVocabUsageCount());

        return userProfile;
    }
}
