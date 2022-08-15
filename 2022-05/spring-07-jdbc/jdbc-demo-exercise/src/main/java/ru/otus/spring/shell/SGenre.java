package ru.otus.spring.shell;

import ru.otus.spring.model.Genre;

import java.util.List;

public interface SGenre {

    public void getGenre();

    public void getGenreCount();

    public void getGenreGetId();

    void gPrint(List<Genre> genres);

}
