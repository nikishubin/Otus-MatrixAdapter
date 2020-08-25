package ru.otus.patterns.adapter.usecase.async;

import java.util.concurrent.ThreadFactory;

public class NamedWorkerFactory implements ThreadFactory {

    private final String name;
    private int counter;

    public NamedWorkerFactory(String workerName) {
        this.counter = 0;
        this.name = workerName;
    }

    @Override
    public Thread newThread(Runnable run) {
        return new Thread(run, String.format("%s worker No %d", name, ++counter));
    }
}
