package ru.otus.patterns.adapter.matrix.to.file;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.otus.patterns.adapter.domain.Matrix;
import ru.otus.patterns.adapter.external.writer.FileWriter;
import ru.otus.patterns.adapter.usecase.ports.MatrixOutput;

import java.io.IOException;

public class MatrixToFile implements MatrixOutput {
    private final Logger log = LogManager.getLogger(MatrixToFile.class);

    private final FileWriter fileWriter;

    public MatrixToFile(FileWriter fileWriter) {
        this.fileWriter = fileWriter;
    }

    @Override
    public void write(Matrix matrix) throws IOException {
        fileWriter.write(matrix.toString().getBytes());
        log.info("Matrix is successfully written to file!");
    }
}
