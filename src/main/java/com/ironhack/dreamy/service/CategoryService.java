package com.ironhack.dreamy.service;

import com.ironhack.dreamy.dto.request.CategoryRequest;
import com.ironhack.dreamy.dto.response.CategoryResponse;
import com.ironhack.dreamy.entity.Category;
import com.ironhack.dreamy.mapper.CategoryMapper;
import com.ironhack.dreamy.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryResponse createCategory(CategoryRequest request) {
        Category category = categoryMapper.toEntity(request);

        Category savedCategory = categoryRepository.save(category);

        return categoryMapper.toResponse(savedCategory);
    }

    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toResponse)
                .toList();
    }
}
