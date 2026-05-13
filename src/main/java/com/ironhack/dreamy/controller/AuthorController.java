package com.ironhack.dreamy.controller;

import com.ironhack.dreamy.dto.request.AuthorRequest;
import com.ironhack.dreamy.dto.response.AuthorResponse;
import com.ironhack.dreamy.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<AuthorResponse>> getAllAuthors() {

        List<AuthorResponse> authors = authorService.getAllAuthors();

        return ResponseEntity.ok(authors);
    }

    @PostMapping
    public ResponseEntity<AuthorResponse> createAuthor(@Valid @RequestBody AuthorRequest request) {

        AuthorResponse createdAuthor = authorService.createAuthor(request);

        return new ResponseEntity<>(createdAuthor, HttpStatus.CREATED);
    }
}