package com.ironhack.dreamy.controller;

import com.ironhack.dreamy.dto.request.BookRequest;
import com.ironhack.dreamy.entity.Author;
import com.ironhack.dreamy.entity.Book;
import com.ironhack.dreamy.entity.Category;
import com.ironhack.dreamy.repository.AuthorRepository;
import com.ironhack.dreamy.repository.BookRepository;
import com.ironhack.dreamy.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Author savedAuthor;
    private Category savedCategory;

    @BeforeEach
    void setup() {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
        categoryRepository.deleteAll();

        savedAuthor = authorRepository.save(Author.builder()
                .fullName("F. Scott Fitzgerald")
                .biography("American novelist")
                .build());

        savedCategory = categoryRepository.save(Category.builder()
                .name("Fiction")
                .build());
    }

    @Test
    @DisplayName("Should return all books")
    void shouldReturnAllBooks() throws Exception {
        bookRepository.save(Book.builder()
                .title("The Great Gatsby")
                .isbn("9780743273565")
                .stockQuantity(10)
                .author(savedAuthor)
                .category(savedCategory)
                .build());

        bookRepository.save(Book.builder()
                .title("Tender Is the Night")
                .isbn("9780684801544")
                .stockQuantity(5)
                .author(savedAuthor)
                .category(savedCategory)
                .build());

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    @DisplayName("Should return 404 when book not found")
    void shouldReturn404WhenBookNotFound() throws Exception {
        mockMvc.perform(get("/api/books/999999"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Should create a book and return 201")
    void shouldCreateBook() throws Exception {
        BookRequest request = new BookRequest();
        request.setTitle("The Great Gatsby");
        request.setIsbn("9780743273565");
        request.setStockQuantity(10);
        request.setAuthorId(savedAuthor.getId());
        request.setCategoryId(savedCategory.getId());

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("The Great Gatsby"))
                .andExpect(jsonPath("$.isbn").value("9780743273565"))
                .andExpect(jsonPath("$.authorName").value("F. Scott Fitzgerald"));
    }

    @Test
    @DisplayName("Should return 409 when ISBN already exists")
    void shouldReturn409WhenDuplicateIsbn() throws Exception {
        bookRepository.save(Book.builder()
                .title("The Great Gatsby")
                .isbn("9780743273565")
                .stockQuantity(10)
                .author(savedAuthor)
                .category(savedCategory)
                .build());

        BookRequest request = new BookRequest();
        request.setTitle("Another Book");
        request.setIsbn("9780743273565");
        request.setStockQuantity(5);
        request.setAuthorId(savedAuthor.getId());
        request.setCategoryId(savedCategory.getId());

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isConflict());
    }

    @Test
    @DisplayName("Should delete book and return 204")
    void shouldDeleteBook() throws Exception {
        Book saved = bookRepository.save(Book.builder()
                .title("The Great Gatsby")
                .isbn("9780743273565")
                .stockQuantity(10)
                .author(savedAuthor)
                .category(savedCategory)
                .build());

        mockMvc.perform(delete("/api/books/" + saved.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/books/" + saved.getId()))
                .andExpect(status().isNotFound());
    }
}
