package ru.otus.patterns.adapter.random;

import ru.otus.patterns.adapter.domain.Matrix;
import ru.otus.patterns.adapter.external.randomizer.MultidimensionalRandomizer;
import ru.otus.patterns.adapter.usecase.ports.MatrixInput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RandomToMatrix implements MatrixInput {
    private static final int DEFAULT_ROW_COUNT = 4;
    private static final int DEFAULT_COLUMN_COUNT = 4;
    private static final int DEFAULT_ITERATIONS = 2;

    private final int rowCount;
    private final int colCount;
    private final int iterations;
    private final MultidimensionalRandomizer randomizer;

    public RandomToMatrix(MultidimensionalRandomizer randomizer) {
        this.randomizer = randomizer;
        this.rowCount = DEFAULT_ROW_COUNT;
        this.colCount = DEFAULT_COLUMN_COUNT;
        this.iterations = DEFAULT_ITERATIONS;
    }

    public RandomToMatrix(int rowCount, int colCount, int iterations, MultidimensionalRandomizer randomizer) {
        this.rowCount = rowCount;
        this.colCount = colCount;
        this.iterations = iterations;
        this.randomizer = randomizer;
    }

    @Override
    public Matrix read() {
        int[][] unparsedMatrix = randomizer.construct(rowCount, colCount);
        return getMatrixFromUnparsedMultidimensionalArray(unparsedMatrix);
    }

    @Override
    public List<Matrix> readAll() {
        int iterationCount = Math.max(iterations, DEFAULT_ITERATIONS);

        List<Matrix> matrices = new ArrayList<>();
        for (int i = 0; i < iterationCount; i++) {
            int[][] unparsedMatrix = randomizer.construct(rowCount, colCount);
            matrices.add(getMatrixFromUnparsedMultidimensionalArray(unparsedMatrix));
        }
        return matrices;
    }

    private Matrix getMatrixFromUnparsedMultidimensionalArray(int[][] unparsedMatrix) {
        Matrix.Builder builder = Matrix.newInstance();
        Arrays.stream(unparsedMatrix).forEach(row -> {
            Integer[] elements = Arrays.stream(row).boxed().toArray(Integer[]::new);
            builder.addRow(elements);
        });
        return builder.create();
    }
}
