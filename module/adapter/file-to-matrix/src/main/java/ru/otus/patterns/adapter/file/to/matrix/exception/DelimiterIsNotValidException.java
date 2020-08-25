package ru.otus.patterns.adapter.file.to.matrix.exception;

public final class DelimiterIsNotValidException extends RuntimeException {

    public DelimiterIsNotValidException(String message) {
        super(message);
    }
}
