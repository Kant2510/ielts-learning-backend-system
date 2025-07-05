package com.il.quizmodule.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tag_search")
public class SearchTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private Boolean isShown;
}
