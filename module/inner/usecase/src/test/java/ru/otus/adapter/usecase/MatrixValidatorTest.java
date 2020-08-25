package ru.otus.adapter.usecase;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.otus.patterns.adapter.domain.Matrix;
import ru.otus.patterns.adapter.usecase.exception.OperationIsNotValidException;
import ru.otus.patterns.adapter.usecase.validation.MatrixValidator;

class MatrixValidatorTest {

    @Test
    void whenMatrixDimensionIsNotValidThenThrowOperationIsNotValid() {
        Matrix first = Matrix.newInstance()
                .addRow(1, 1)
                .create();

        Matrix second = Matrix.newInstance()
                .addRow(1, 1)
                .addRow(1, 1)
                .create();

        Assertions.assertThatThrownBy(() -> MatrixValidator.additionAvailable(first, second)).isInstanceOf(OperationIsNotValidException.class);
    }
}
