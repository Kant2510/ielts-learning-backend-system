package com.il.quizmodule.repository;

import java.util.List;

public interface QuizCustomRepo {
    List<Integer> findQuizIdsMatchingAllTags(List<Integer> tagIds);
}
