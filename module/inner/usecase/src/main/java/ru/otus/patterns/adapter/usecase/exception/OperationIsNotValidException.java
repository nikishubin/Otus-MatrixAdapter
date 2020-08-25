package ru.otus.patterns.adapter.usecase.exception;

public final class OperationIsNotValidException extends RuntimeException {

    public OperationIsNotValidException(String message) {
        super(message);
    }
}
