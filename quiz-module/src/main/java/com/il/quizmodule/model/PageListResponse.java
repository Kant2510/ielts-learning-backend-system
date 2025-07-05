package com.il.quizmodule.model;

import java.util.List;

//public class PageListResponse<T> {
//    public PageListResponse(int total, int page, int size, List<T> items) {
//        // The total number of items across all pages
//        // The current page number (1-based index)
//        // The current page number (0-based index)
//        // The list of items on the current page
//    }
//}
// To record
public record PageListResponse<T>(
        int total, // The total number of items across all pages
        int page, // The current page number (1-based index)
        int size, // The current page size
        List<T> items // The list of items on the current page
) {}