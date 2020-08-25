package ru.otus.patterns.adapter.usecase.ports;

import ru.otus.patterns.adapter.domain.Matrix;

import java.io.IOException;

public interface MatrixOutput {

    void write(Matrix matrix) throws IOException;
}
