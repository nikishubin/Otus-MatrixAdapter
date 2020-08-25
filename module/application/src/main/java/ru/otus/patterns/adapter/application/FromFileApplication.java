package ru.otus.patterns.adapter.application;

import ru.otus.patterns.adapter.application.properties.PropertiesSelector;
import ru.otus.patterns.adapter.domain.Matrix;
import ru.otus.patterns.adapter.external.reader.impl.FileStreamReader;
import ru.otus.patterns.adapter.external.writer.impl.OutputStreamWriter;
import ru.otus.patterns.adapter.file.to.matrix.FileToMatrix;
import ru.otus.patterns.adapter.matrix.to.file.MatrixToFile;
import ru.otus.patterns.adapter.usecase.ports.MatrixInput;
import ru.otus.patterns.adapter.usecase.ports.MatrixOutput;

import java.io.IOException;
import java.util.List;

public class FromFileApplication extends MatrixApplication {
    private static final String INPUT_PATH_KEY = "input.path";

    public static void main(String[] args) throws IOException {
        String inputFilePath = PropertiesSelector.getProperty(MatrixApplication.PROPERTIES, INPUT_PATH_KEY);
        String outputFilePath = PropertiesSelector.getProperty(MatrixApplication.PROPERTIES, MatrixApplication.OUTPUT_PATH_KEY);

        MatrixInput matrixInput = new FileToMatrix(inputFilePath, new FileStreamReader());
        MatrixOutput matrixOutput = new MatrixToFile(new OutputStreamWriter(outputFilePath));

        List<Matrix> matrices = matrixInput.readAll();
        matrixOutput.write(sum(matrices));
    }

}
