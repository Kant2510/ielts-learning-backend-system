package com.il.quizmodule.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.il.quizmodule.repository.QuizSpecification;
import com.il.quizmodule.dto.QuizResponseDTO;
import com.il.quizmodule.mapper.QuizMapper;
import com.il.quizmodule.model.GetQuizzesParam;
import com.il.quizmodule.model.PageListResponse;
import com.il.quizmodule.model.Quiz;
import com.il.quizmodule.repository.QuizRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class QuizService {
    private final QuizRepo quizRepo;
    private final QuizMapper quizMapper;

    public QuizService(QuizRepo quizRepo, QuizMapper quizMapper) {
        this.quizRepo = quizRepo;
        this.quizMapper = quizMapper;
    }

    // Define methods for quiz operations here
    public PageListResponse<QuizResponseDTO> getAllQuizzes(String userId, GetQuizzesParam params) {
        // Validate userId if necessary
        if (userId == null || userId.isBlank()) {
            throw new IllegalArgumentException("User ID cannot be null or blank");
        }
        PageRequest pageable = PageRequest.of(params.page_number(), params.page_size(), Sort.by(params.sort_by()).descending());
        List<Specification<Quiz>> specs = new ArrayList<>();
        // Validate userId if necessary
        if (params.tag_passage() != null ||
            params.tag_question() != null ||
            params.tag_section() != null) {
            List<Integer> tagIds = new ArrayList<>();
            if (params.tag_passage() != null) {
                tagIds.add(params.tag_passage());
            }
            if (params.tag_question() != null) {
                tagIds.add(params.tag_question());
            }
            if (params.tag_section() != null) {
                tagIds.add(params.tag_section());
            }
            List<Integer> quizIds = quizRepo.findQuizIdsMatchingAllTags(tagIds);
            if (quizIds.isEmpty()) {
                return new PageListResponse<>(0, 1, 0, new ArrayList<>());
            }
            specs.add(QuizSpecification.hasIdIn(quizIds));
        }
        if (params.type() != null) {
            specs.add(QuizSpecification.hasType(params.type()));
        }
        if (params.status() != null) {
            specs.add(QuizSpecification.isPublic(params.status().equals("public")));
        }
        if (params.level() != null) {
            specs.add(QuizSpecification.hasLevel(params.level()));
        }
        if (params.search() != null && !params.search().isBlank()) {
            String search = params.search().toLowerCase();
            specs.add(QuizSpecification.hasTitleLike(search));
        }
        // Combine tất cả bằng AND
        Specification<Quiz> combinedSpec = specs.stream()
                .filter(Objects::nonNull)
                .reduce(Specification::and)
                .orElse(null);
        Page<Quiz> quizPage = quizRepo.findAll(combinedSpec, pageable);
        if (quizPage.isEmpty()) {
            return new PageListResponse<>(0, 1, 0, new ArrayList<>());
        }
        //log content of quizPage
        quizPage.getContent().stream().map(quiz -> quiz.getId().toString()).forEach(System.out::println);
        List<QuizResponseDTO> quizResponseDTOs = quizPage.getContent().stream().map(quizMapper::toResponseDto).toList();
        return new PageListResponse<>(
                (int) quizPage.getTotalElements(),
                quizPage.getNumber(),
                 quizPage.getTotalPages(),
                quizResponseDTOs
        );
    }
}
