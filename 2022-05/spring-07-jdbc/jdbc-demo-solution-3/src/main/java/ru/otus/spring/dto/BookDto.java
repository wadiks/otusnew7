package ru.otus.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    public Long id;

    @NotBlank()
    @Size(min = 2, max = 10)
    public String name;

    @Size(min = 2, max = 200)
    public String genres;

    private String authors;
/*
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

 */

}
