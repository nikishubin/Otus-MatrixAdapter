package ru.otus.patterns.adapter.application;

import ru.otus.patterns.adapter.domain.Matrix;
import ru.otus.patterns.adapter.usecase.MatrixMathematicalOperations;

import java.util.List;

abstract class MatrixApplication {
    protected static final String PROPERTIES = "application.properties";
    protected static final String OUTPUT_PATH_KEY = "output.path";

    protected static final MatrixMathematicalOperations mathematicalOperations;

    static {
        mathematicalOperations = new MatrixMathematicalOperations();
    }

    protected MatrixApplication() {
    }

    protected static Matrix sum(List<Matrix> matrices) {
        Matrix result = matrices.get(0);
        for (int i = 1; i < matrices.size(); i++) {
            result = mathematicalOperations.addition(result, matrices.get(i));
        }
        return result;
    }
}
