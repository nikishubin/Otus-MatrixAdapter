module otus.matrix.application {

    requires otus.matrix.domain;
    requires otus.matrix.usecase;

    requires external.file.reader;
    requires otus.adapter.file.to.matrix;

    requires external.matrix.randomizer;
    requires otus.adapter.random.to.matrix;

    requires external.file.writer;
    requires otus.adapter.matrix.to.file;
}