package ru.otus.adapter.usecase;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.otus.patterns.adapter.domain.Matrix;
import ru.otus.patterns.adapter.usecase.MatrixMathematicalOperations;

import java.util.Collection;

class MatrixMathematicalOperationsTest {

    private static MatrixMathematicalOperations matrixOperations;

    @BeforeAll
    static void beforeAll() {
        matrixOperations = new MatrixMathematicalOperations();
    }

    @ParameterizedTest
    @MethodSource("ru.otus.adapter.usecase.args.MatrixMathematicalOperationsTestArgs#matrixAdditionData")
    void whenMatrixAdditionIsAvailableThenGetResult(Matrix first, Matrix second, Matrix expected) {
        Matrix actual = matrixOperations.addition(first, second);

        Assertions.assertThat(actual.getRows().stream().flatMap(Collection::stream).toArray())
                .containsExactly(expected.getRows().stream().flatMap(Collection::stream).toArray());
    }
}
