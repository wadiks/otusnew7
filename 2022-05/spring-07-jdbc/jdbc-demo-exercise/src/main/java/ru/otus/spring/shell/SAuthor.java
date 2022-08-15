package ru.otus.spring.shell;

import ru.otus.spring.model.Authors;

import java.util.List;

public interface SAuthor {

    public void getAutors();

    public void getAutorsCount();

    public void getAutorsGetId();
    public void aPrint(List<Authors> authors);
}
