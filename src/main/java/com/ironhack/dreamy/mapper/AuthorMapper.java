package com.ironhack.dreamy.mapper;

import com.ironhack.dreamy.dto.request.AuthorRequest;
import com.ironhack.dreamy.dto.response.AuthorResponse;
import com.ironhack.dreamy.entity.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    public AuthorResponse toResponse(Author author) {
        if (author == null) return null;

        return AuthorResponse.builder()
                .id(author.getId())
                .fullName(author.getFullName())
                .biography(author.getBiography())
                .createdAt(author.getCreatedAt())
                .build();
    }


    public Author toEntity(AuthorRequest request) {
        if (request == null) return null;

        return Author.builder()
                .fullName(request.getFullName())
                .biography(request.getBiography())
                .build();
    }
}