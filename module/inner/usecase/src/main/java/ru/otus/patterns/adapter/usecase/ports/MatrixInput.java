package ru.otus.patterns.adapter.usecase.ports;

import ru.otus.patterns.adapter.domain.Matrix;

import java.io.IOException;
import java.util.List;

public interface MatrixInput {

    Matrix read() throws IOException;

    List<Matrix> readAll() throws IOException;
}
