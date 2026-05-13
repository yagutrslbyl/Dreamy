package com.ironhack.dreamy.service;
import com.ironhack.dreamy.dto.request.BookRequest;
import com.ironhack.dreamy.dto.request.BookUpdateRequest;
import com.ironhack.dreamy.dto.response.BookResponse;
import com.ironhack.dreamy.entity.Book;
import com.ironhack.dreamy.mapper.BookMapper;
import com.ironhack.dreamy.repository.AuthorRepository;
import com.ironhack.dreamy.repository.BookRepository;
import com.ironhack.dreamy.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final BookMapper bookMapper;

    public BookResponse createBook(BookRequest request) {
        if (bookRepository.existsByIsbn(request.getIsbn())) {
            throw new RuntimeException("Book with ISBN " + request.getIsbn() + " already exists!");
        }
        Book book = bookMapper.toEntity(request);

        book.setAuthor(authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found")));

        book.setCategory(categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found")));

        return bookMapper.toResponse(bookRepository.save(book));
    }

    public BookResponse updateBook(Long id, BookUpdateRequest request) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        existingBook.setTitle(request.getTitle());
        existingBook.setStockQuantity(request.getStockQuantity());

        existingBook.setAuthor(authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found")));

        existingBook.setCategory(categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found")));

        return bookMapper.toResponse(bookRepository.save(existingBook));
    }

    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toResponse)
                .collect(Collectors.toList());
    }

    public BookResponse getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
        return bookMapper.toResponse(book);
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book not found");
        }
        bookRepository.deleteById(id);
    }
}

