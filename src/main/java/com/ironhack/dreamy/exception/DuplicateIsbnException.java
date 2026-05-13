package com.ironhack.dreamy.exception;

public class DuplicateIsbnException extends RuntimeException {
    public DuplicateIsbnException(String message)
    {
        super(message);
    }
}
