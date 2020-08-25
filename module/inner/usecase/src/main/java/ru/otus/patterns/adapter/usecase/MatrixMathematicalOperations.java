package ru.otus.patterns.adapter.usecase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.otus.patterns.adapter.domain.Matrix;
import ru.otus.patterns.adapter.usecase.async.NamedWorkerFactory;
import ru.otus.patterns.adapter.usecase.validation.MatrixValidator;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class MatrixMathematicalOperations {
    private final Logger log = LogManager.getLogger(MatrixMathematicalOperations.class);

    private final ThreadFactory namedFactory;

    public MatrixMathematicalOperations() {
        this.namedFactory = new NamedWorkerFactory("MatrixCalculator");
    }

    public Matrix addition(Matrix first, Matrix second) {
        MatrixValidator.additionAvailable(first, second);
        ExecutorService executorPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors(), namedFactory);

        int rowCount = first.getRows().size();
        int colCount = first.getRows().get(0).size();

        var promise = initializePromisedMatrix(rowCount, colCount);
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                Integer firstElement = first.getRows().get(row).get(col);
                Integer secondElement = second.getRows().get(row).get(col);

                log.debug("Calculate matrix sum at {}:{}; first = {} ; second = {}", row, col, firstElement, secondElement);
                promise.get(row).set(col, CompletableFuture.supplyAsync(() -> firstElement + secondElement, executorPool));
            }
        }

        CompletableFuture.allOf(promise.stream().flatMap(Collection::stream).toArray(CompletableFuture[]::new))
                .thenRun(executorPool::shutdown);
        return convertPromiseToResult(promise);
    }

    private List<List<CompletableFuture<Integer>>> initializePromisedMatrix(int rowCount, int colCount) {
        return IntStream.range(0, rowCount).boxed()
                .map(i -> IntStream.range(0, colCount).mapToObj(next -> CompletableFuture.completedFuture(0)).collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    private Matrix convertPromiseToResult(List<List<CompletableFuture<Integer>>> promise) {
        Matrix.Builder matrix = Matrix.newInstance();
        promise.forEach(promisedRow -> matrix.addRow(promisedRow.stream().map(CompletableFuture::join).toArray(Integer[]::new)));
        return matrix.create();
    }
}
