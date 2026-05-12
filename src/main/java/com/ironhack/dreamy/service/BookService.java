package com.ironhack.dreamy.service;
import com.ironhack.dreamy.dto.BookRequest;
import com.ironhack.dreamy.dto.BookResponse;
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

    public BookResponse createBook(BookRequest request) {
        if (bookRepository.existsByIsbn(request.getIsbn())) {
            throw new RuntimeException("Book with ISBN " + request.getIsbn() + " already exists!");
        }
        Book book = BookMapper.toEntity(request);

        book.setAuthor(authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found")));

        book.setCategory(categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found")));

        Book savedBook = bookRepository.save(book);

        return BookMapper.toResponse(savedBook);
    }

    public BookResponse updateBook(Long id, BookRequest request) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        existingBook.setTitle(request.getTitle());
        existingBook.setIsbn(request.getIsbn());
        existingBook.setStockQuantity(request.getStockQuantity());

        existingBook.setAuthor(authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found")));

        existingBook.setCategory(categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found")));

        return BookMapper.toResponse(bookRepository.save(existingBook));
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

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book not found");
        }
        bookRepository.deleteById(id);
    }
}

