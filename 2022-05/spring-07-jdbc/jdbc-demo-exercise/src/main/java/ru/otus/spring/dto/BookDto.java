package ru.otus.spring.dto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.spring.model.Authors;
import ru.otus.spring.model.Books;
import ru.otus.spring.model.Genre;

@NoArgsConstructor
@Data
public class BookDto {
    public Long id;
    public @NotBlank @Size(
            min = 2,
            max = 10
    ) String name;
    public @Size(
            min = 2,
            max = 200
    ) String genres;
    private String authors;

    public List<Genre> setGenreFromString(String genre) {
        return (List) Stream.of(genre.split(",")).map((m) -> {
            return m.trim();
        }).map(Genre::new).collect(Collectors.toList());
    }

    public List<Authors> setAuthorsFromString(String authors) {
        return (List) Stream.of(authors.split(",")).map((m) -> {
            String[] array = m.trim().split(" ");
            return null != array[0] && !array[0].isEmpty() && null != array[1] && !array[1].isEmpty() ? new Authors(array[0], array[1]) : null;
        }).collect(Collectors.toList());
    }

    public Books toDomainObject() {
        return new Books(this.id, this.name, this.setGenreFromString(this.genres), this.setAuthorsFromString(this.authors));
    }


    public BookDto(final Long id, final String name, final String genres, final String authors) {
        this.id = id;
        this.name = name;
        this.genres = genres;
        this.authors = authors;
    }
}