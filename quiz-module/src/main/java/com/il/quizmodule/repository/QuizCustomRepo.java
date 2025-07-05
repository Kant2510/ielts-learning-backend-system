package com.il.quizmodule.repository;

import java.util.List;
import java.util.UUID;

public interface QuizCustomRepo {
    List<Integer> findQuizIdsMatchingAllTags(List<Integer> tagIds);
}
