package com.ironhack.dreamy.controller;

import com.ironhack.dreamy.dto.request.CategoryRequest;
import com.ironhack.dreamy.dto.response.CategoryResponse;
import com.ironhack.dreamy.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        List<CategoryResponse> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CategoryRequest request) {
        CategoryResponse createdCategory = categoryService.createCategory(request);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }
}
