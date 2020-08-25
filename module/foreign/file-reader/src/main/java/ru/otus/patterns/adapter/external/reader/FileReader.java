package ru.otus.patterns.adapter.external.reader;

import java.io.IOException;

public interface FileReader {

    byte[] read(String path) throws IOException;
}
