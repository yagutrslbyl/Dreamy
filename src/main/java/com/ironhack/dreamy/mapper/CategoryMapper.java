package com.ironhack.dreamy.mapper;

import com.ironhack.dreamy.dto.request.CategoryRequest;
import com.ironhack.dreamy.dto.response.CategoryResponse;
import com.ironhack.dreamy.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryResponse toResponse(Category category) {
        if (category == null) return null;

        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    // Request DTO -> Entity
    public Category toEntity(CategoryRequest request) {
        if (request == null) return null;

        return Category.builder()
                .name(request.getName())
                .build();
    }
}
