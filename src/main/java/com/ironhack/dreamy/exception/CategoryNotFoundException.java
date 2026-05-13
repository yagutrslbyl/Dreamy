package com.ironhack.dreamy.exception;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(String message) {
        super(message);
    }
}
