package com.ironhack.dreamy.service;

import com.ironhack.dreamy.dto.BookRequest;
import com.ironhack.dreamy.dto.BookResponse;
import com.ironhack.dreamy.entity.Book;
import com.ironhack.dreamy.mapper.BookMapper;
import com.ironhack.dreamy.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public BookResponse createBook(BookRequest request) {
        if (bookRepository.existsByIsbn(request.getIsbn())) {
            throw new RuntimeException("Book with ISBN " + request.getIsbn() + " already exists!");
        }

        Book book = BookMapper.toEntity(request);

        Book savedBook = bookRepository.save(book);

        return BookMapper.toResponse(savedBook);
    }

    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(BookMapper::toResponse)
                .collect(Collectors.toList());
    }

    public BookResponse getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
        return BookMapper.toResponse(book);
    }
}
