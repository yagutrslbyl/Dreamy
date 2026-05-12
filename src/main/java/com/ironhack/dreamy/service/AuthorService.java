package com.ironhack.dreamy.service;

import com.ironhack.dreamy.entity.Author;
import com.ironhack.dreamy.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + id));
    }

    public List<Author> searchAuthorsByName(String fullName) {
        return authorRepository.findByFullNameContainingIgnoreCase(fullName);
    }

    public Author updateAuthor(Long id, Author updatedAuthor) {
        Author author = getAuthorById(id);

        author.setFullName(updatedAuthor.getFullName());
        author.setBiography(updatedAuthor.getBiography());

        return authorRepository.save(author);
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}