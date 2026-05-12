package com.ironhack.dreamy.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name="books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    @Size(min=2, message="Title must be at least 2 characters")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "ISBN is required")
    @Column(nullable = false,unique = true)
    private String isbn;

    @Min(value=0, message = "Stock cannot be negative")
    private Integer stockQuantity;

    @ManyToOne
    @JoinColumn(name="author_id")
    private Author author;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;
}