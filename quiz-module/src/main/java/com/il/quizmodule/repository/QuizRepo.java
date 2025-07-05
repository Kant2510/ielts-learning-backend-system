package com.il.quizmodule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.il.quizmodule.model.Quiz;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface QuizRepo extends JpaRepository<Quiz, UUID>, JpaSpecificationExecutor<Quiz>, QuizCustomRepo {
//    Optional<Quiz> findByIdIn(List<UUID> ids);
//    Optional<Quiz> findMatchingQuizByIdAndUserId(UUID id, String userId);
}
