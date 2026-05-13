package com.ironhack.dreamy.exception;
import com.ironhack.dreamy.dto.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({BookNotFoundException.class, AuthorNotFoundException.class, CategoryNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleNotFound(RuntimeException ex, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request);
    }

    @ExceptionHandler(DuplicateIsbnException.class)
    public ResponseEntity<ErrorResponse> handleConflict(DuplicateIsbnException ex, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.CONFLICT, ex.getMessage(), request);
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(InvalidDataException ex, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(HttpStatus status, String message, HttpServletRequest request) {
        ErrorResponse error = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(message)
                .path(request.getRequestURI())
                .build();
        return new ResponseEntity<>(error, status);
    }
}
