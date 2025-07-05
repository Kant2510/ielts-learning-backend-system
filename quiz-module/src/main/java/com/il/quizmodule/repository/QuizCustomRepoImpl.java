package com.il.quizmodule.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuizCustomRepoImpl implements QuizCustomRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Integer> findQuizIdsMatchingAllTags(List<Integer> tagIds) {
//        String sql = """
//            SELECT quiz_id
//            FROM quiz_tag_search
//            WHERE quiz_id IS NOT NULL
//            GROUP BY quiz_id
//            HAVING ARRAY[:tagIds]::int[] <@ ARRAY_AGG(tag_search_id)
//        """;
        String jpql = """
            SELECT qs.quiz.id
            FROM Quiz_SearchTag qs
            WHERE qs.searchTag.id IN :tagIds
            GROUP BY qs.quiz.id
            HAVING COUNT(DISTINCT qs.id) = :size
        """;
//        List<UUID> quizIds = entityManager.createNativeQuery(sql)
//                .setParameter("tagIds", tagIds)
//                .getResultList();

//        return quizIds;
        return entityManager.createQuery(jpql, Integer.class)
                .setParameter("tagIds", tagIds)
                .setParameter("size", tagIds.size())
                .getResultList();
    }
}

