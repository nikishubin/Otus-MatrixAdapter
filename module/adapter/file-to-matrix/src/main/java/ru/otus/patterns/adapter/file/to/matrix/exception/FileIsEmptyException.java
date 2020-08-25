package ru.otus.patterns.adapter.file.to.matrix.exception;

public final class FileIsEmptyException extends RuntimeException {

    public FileIsEmptyException(String message) {
        super(message);
    }
}
