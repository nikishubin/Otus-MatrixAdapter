package ru.otus.patterns.adapter.external.randomizer.impl;

import ru.otus.patterns.adapter.external.randomizer.MultidimensionalRandomizer;

import java.util.Random;

public class MatrixRandomizer implements MultidimensionalRandomizer {
    private final Random randomizer;
    private final int bound;

    public MatrixRandomizer(int bound) {
        this.randomizer = new Random();
        this.bound = bound;
    }

    @Override
    public int[][] construct(int rowCount, int colCount) {
        int[][] result = new int[rowCount][colCount];

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                result[i][j] = randomizer.nextInt(bound);
            }
        }

        return result;
    }
}
