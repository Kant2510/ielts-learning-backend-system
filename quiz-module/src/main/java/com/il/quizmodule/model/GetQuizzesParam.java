package com.il.quizmodule.model;

public record GetQuizzesParam(
        Integer page_number,
        Integer page_size,
        Integer tag_section,
        Integer tag_passage,
        Integer tag_question,
        Integer submitted_status,
        Integer type,
        Integer level,
        String status,
        String search,
        String sort_by
) {}
