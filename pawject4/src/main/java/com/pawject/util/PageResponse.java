package com.pawject.util;

import java.util.List;

import lombok.Data;

@Data
public class PageResponse<T> {

    private List<T> list;

    private int page;        // 현재 페이지
    private int total;       // 전체 글 수
    private int totalPages;  // 전체 페이지 수

    private boolean hasPrev;
    private boolean hasNext;
}