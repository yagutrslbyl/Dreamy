package com.ironhack.dreamy.mapper;

import com.ironhack.dreamy.dto.request.BookRequest;
import com.ironhack.dreamy.dto.response.BookResponse;
import com.ironhack.dreamy.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    public Book toEntity(BookRequest request) {
        if (request == null) return null;

        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setIsbn(request.getIsbn());
        book.setStockQuantity(request.getStockQuantity());
        return book;
    }

    public BookResponse toResponse(Book book) {
        if (book == null) return null;

        BookResponse response = new BookResponse();
        response.setId(book.getId());
        response.setTitle(book.getTitle());
        response.setIsbn(book.getIsbn());
        response.setStockQuantity(book.getStockQuantity());

        if (book.getAuthor() != null) {
            response.setAuthorName(book.getAuthor().getFullName());
        }
        if (book.getCategory() != null) {
            response.setCategoryName(book.getCategory().getName());
        }

        response.setCreatedAt(book.getCreatedAt());

        return response;
    }

}
