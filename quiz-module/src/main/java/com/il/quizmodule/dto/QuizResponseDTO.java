package com.il.quizmodule.dto;

public record QuizResponseDTO(
    int id,
    String status,
    String user_created,
    String user_updated,
    String date_created,
    String date_updated,
    int type,
    String title,
    String content,
    String description,
    int time,
    String listening_file,
    int level,
    int vote_count,
    String thumbnail,
    int mode,
    boolean is_public
) {}
