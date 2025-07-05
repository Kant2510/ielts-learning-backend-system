package com.il.quizmodule.mapper;

import com.il.quizmodule.dto.QuizResponseDTO;
import com.il.quizmodule.model.Quiz;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuizMapper {
    //Entity -> DTO
    QuizResponseDTO toResponseDto(Quiz quiz);
}
