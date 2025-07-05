package com.il.quizmodule.controller;

import com.il.quizmodule.dto.QuizResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.il.quizmodule.model.GetQuizzesParam;
import com.il.quizmodule.model.PageListResponse;
import com.il.quizmodule.service.QuizService;

@RestController
public class QuizController {
    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    // Define endpoints for quiz operations here,
    // For example, to get all quizzes:
     @GetMapping
     public PageListResponse<QuizResponseDTO> getAllQuizzes(@RequestHeader("userId") String userId, GetQuizzesParam params) {
        // log the parameters
        System.out.println("Received request to get all quizzes with parameters: \n" + params);
        return quizService.getAllQuizzes(userId, params);
     }

}
