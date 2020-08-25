package ru.otus.patterns.adapter.application;

import ru.otus.patterns.adapter.application.properties.PropertiesSelector;
import ru.otus.patterns.adapter.domain.Matrix;
import ru.otus.patterns.adapter.external.randomizer.impl.MatrixRandomizer;
import ru.otus.patterns.adapter.external.writer.impl.OutputStreamWriter;
import ru.otus.patterns.adapter.matrix.to.file.MatrixToFile;
import ru.otus.patterns.adapter.random.RandomToMatrix;
import ru.otus.patterns.adapter.usecase.ports.MatrixInput;
import ru.otus.patterns.adapter.usecase.ports.MatrixOutput;

import java.io.IOException;
import java.util.List;

public class FromRandomizerApplication extends MatrixApplication {

    public static void main(String[] args) throws IOException {
        String outputFilePath = PropertiesSelector.getProperty(MatrixApplication.PROPERTIES, MatrixApplication.OUTPUT_PATH_KEY);

        MatrixInput matrixInput = new RandomToMatrix(2, 2, 4, new MatrixRandomizer(10));
        MatrixOutput matrixOutput = new MatrixToFile(new OutputStreamWriter(outputFilePath));

        List<Matrix> matrices = matrixInput.readAll();
        matrixOutput.write(sum(matrices));
    }
}
