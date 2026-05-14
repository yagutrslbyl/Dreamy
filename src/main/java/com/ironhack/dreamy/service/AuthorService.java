package com.ironhack.dreamy.service;

import com.ironhack.dreamy.dto.request.AuthorRequest;
import com.ironhack.dreamy.dto.response.AuthorResponse;
import com.ironhack.dreamy.entity.Author;
import com.ironhack.dreamy.exception.DuplicateResourceException;
import com.ironhack.dreamy.mapper.AuthorMapper;
import com.ironhack.dreamy.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorResponse createAuthor(AuthorRequest request) {

        if (authorRepository.existsByFullNameIgnoreCase(request.getFullName())) {
            throw new DuplicateResourceException("Author already exists");
        }

        Author author = authorMapper.toEntity(request);

        Author savedAuthor = authorRepository.save(author);

        return authorMapper.toResponse(savedAuthor);
    }

    public List<AuthorResponse> getAllAuthors() {

        return authorRepository.findAll().stream()
                .map(authorMapper::toResponse)
                .toList();
    }
}