package ru.otus.spring.shell;

import ru.otus.spring.model.Genre;
import java.util.List;

public interface IGenre {

    public void getGenre();

    public void getGenreCount();

    public void getGenreGetId();

    public void gPrint(List<Genre> genres);


}
