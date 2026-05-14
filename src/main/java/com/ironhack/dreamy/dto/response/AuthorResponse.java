package com.ironhack.dreamy.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorResponse {

    private Long id;
    private String fullName;
    private String biography;
    private LocalDateTime createdAt;
}