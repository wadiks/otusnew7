package ru.otus.spring.dto;

import ru.otus.spring.model.Authors;
import ru.otus.spring.model.Books;
import ru.otus.spring.model.Genre;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BookDto {

    public Long id;

    @NotBlank()
    @Size(min = 2, max = 10)
    public String name;

    @Size(min = 2, max = 200)
    public String genres;

    @Size(min = 2, max = 200)
    private String authors;



    public BookDto() {
    }

    public BookDto(String name) {
        this.name = name;
    }

    public BookDto(Long id, String name, String genre, String authors) {
        this.id = id;
        this.name = name;
        this.genres = genre;
        this.authors = authors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getGenres() {
        return genres;
    }

    public String getAuthors() {
        return authors;
    }


    public List<Genre> setGenreFromString(String genre) {
        return Stream.of(genre.split(","))
                .map(m -> m.trim())
                .map(Genre::new)
                .collect(Collectors.toList());
    }

    public List<Authors> setAuthorsFromString(String authors) {
        return Stream.of(authors.split(","))
                .map(m -> {
                    var array = m.trim().split(" ");
                    if ((null != array[0] && !array[0].isEmpty()) && (null != array[1] && !array[1].isEmpty()))
                        return new Authors(array[0], array[1]);
                    else return null;
                })
                .collect(Collectors.toList());
    }

    public Books toDomainObject() {
        return new Books(id, name, setGenreFromString(genres), setAuthorsFromString(authors));
    }

    public static BookDto fromDomainObject(Books books) {
        return new BookDto(books.getId(), books.getName(), books.getGenre(), books.getAuthor());
    }
}
