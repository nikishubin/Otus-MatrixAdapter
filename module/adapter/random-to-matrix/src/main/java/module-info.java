module otus.adapter.random.to.matrix {

    requires otus.matrix.domain;
    requires otus.matrix.usecase;
    requires external.matrix.randomizer;

    exports ru.otus.patterns.adapter.random;
}