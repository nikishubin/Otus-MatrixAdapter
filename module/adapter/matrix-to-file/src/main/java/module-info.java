module otus.adapter.matrix.to.file {

    requires org.apache.logging.log4j;

    requires otus.matrix.domain;
    requires otus.matrix.usecase;
    requires external.file.writer;

    exports ru.otus.patterns.adapter.matrix.to.file;
}