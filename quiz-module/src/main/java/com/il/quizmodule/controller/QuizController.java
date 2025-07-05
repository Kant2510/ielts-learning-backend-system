package com.il.quizmodule.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.il.quizmodule.model.GetQuizzesParam;
import com.il.quizmodule.model.PageListResponse;
import com.il.quizmodule.dto.QuizResponseDTO;
import com.il.quizmodule.service.QuizService;

@RestController
@Tag(name = "Quiz API", description = "APIs for quiz management")
public class QuizController {
    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    // Define endpoints for quiz operations here,
    // For example, to get all quizzes:
     @GetMapping
     @Operation(summary = "Get all quizzes")
     public PageListResponse<QuizResponseDTO> getAllQuizzes(@RequestHeader("userId") String userId, GetQuizzesParam params) {
        // log the parameters
        System.out.println("Received request to get all quizzes with parameters: \n" + params);
        return quizService.getAllQuizzes(userId, params);
     }

}
