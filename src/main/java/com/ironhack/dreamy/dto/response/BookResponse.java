package com.ironhack.dreamy.dto.response;

import lombok.Data;

@Data
public class BookResponse {
    private Long id;
    private String title;
    private String isbn;
    private int stockQuantity;
    private String authorName;
    private String categoryName;
}
