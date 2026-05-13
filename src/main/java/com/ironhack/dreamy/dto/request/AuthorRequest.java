package com.ironhack.dreamy.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorRequest {

    @NotBlank(message = "Author full name is required")
    @Size(min = 3, max = 100, message = "Author name must be between 3 and 100 characters")
    private String fullName;

    @Size(max = 1000, message = "Biography cannot exceed 1000 characters")
    private String biography;
}