package com.il.quizmodule.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@Entity
@Table(name = "quiz")
@NoArgsConstructor
@AllArgsConstructor
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String status;

    @NotNull
    @Column(name = "user_created")
    private UUID user_created;

    @NotNull
    @Column(name = "user_updated")
    private UUID user_updated;

    @NotNull
    @Column(name = "date_created")
    private Timestamp date_created;

    @NotNull
    @Column(name = "date_updated")
    private Timestamp date_updated;

    @NotNull
    private int type;

    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    private String description;

    @NotNull
    private int time;

    private String listening_file;

    @NotNull
    private int level;

    @NotNull
    private int vote_count;

    private String thumbnail;

    @NotNull
    private int mode;

    @NotNull
    private Boolean is_public;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<Quiz_SearchTag> quiz_search_tags;
}
