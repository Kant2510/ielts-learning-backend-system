package com.il.usermodule.mapper;

import com.il.usermodule.dto.UserResponseDTO;
import com.il.usermodule.dto.UserTargetResponseDTO;
import com.il.usermodule.model.UserProfile;
import com.il.usermodule.model.UserTarget;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapperV2 {
    // Entity -> DTO
    UserResponseDTO toResDto(UserProfile users);
    UserTargetResponseDTO toResto(UserTarget userTarget);
}
