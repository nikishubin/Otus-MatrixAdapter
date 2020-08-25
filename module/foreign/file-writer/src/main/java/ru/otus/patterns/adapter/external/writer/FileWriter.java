package ru.otus.patterns.adapter.external.writer;

import java.io.IOException;

public interface FileWriter {

    void write(byte[] target) throws IOException;
}
