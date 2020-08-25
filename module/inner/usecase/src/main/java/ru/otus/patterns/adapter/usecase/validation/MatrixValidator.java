package ru.otus.patterns.adapter.usecase.validation;

import ru.otus.patterns.adapter.domain.Matrix;
import ru.otus.patterns.adapter.usecase.exception.OperationIsNotValidException;

public class MatrixValidator {

    private MatrixValidator() {
    }

    public static void additionAvailable(Matrix first, Matrix second) {
        if (first.getRows().size() != second.getRows().size() || first.getRows().get(0).size() != second.getRows().get(0).size()) {
            throw new OperationIsNotValidException("Addition elements must be of the same dimension!");
        }
    }
}
