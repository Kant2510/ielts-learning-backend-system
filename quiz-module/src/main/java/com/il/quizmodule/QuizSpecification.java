package com.il.quizmodule;

import com.il.quizmodule.model.Quiz;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.UUID;

public class QuizSpecification {
    public static Specification<Quiz> hasType(Integer type) {
        return (root, query, cb) -> {
            if (type == null) return null;
            return cb.equal(root.get("type"), type);
        };
    }

    public static Specification<Quiz> hasIdIn(List<Integer> ids) {
        return (root, query, cb) -> {
            if (ids.isEmpty()) return null;
            return root.get("id").in(ids);
        };
    }

    public static Specification<Quiz> isPublic(Boolean isPublic) {
        return (root, query, cb) -> {
            if (isPublic == null) return null;
            return cb.equal(root.get("is_public"), isPublic);
        };
    }

    public static Specification<Quiz> hasLevel(Integer level) {
        return (root, query, cb) -> {
            if (level == null) return null;
            return cb.equal(root.get("level"), level);
        };
    }

    public static Specification<Quiz> hasTitleLike(String title) {
        return (root, query, cb) -> {
            if (title == null || title.isEmpty()) return null;
            return cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%");
        };
    }
}
