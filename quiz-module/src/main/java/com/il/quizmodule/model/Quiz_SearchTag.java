package com.il.quizmodule.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "quiz_tag_search")
public class Quiz_SearchTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    @NotNull
    private Quiz quiz;

    @ManyToOne
    @JoinColumn(name = "tag_search_id", nullable = false)
    @NotNull
    private SearchTag searchTag;
}
