package com.ironhack.dreamy.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class CategoryResponse {
    private Long id;
    private String name;
}
