package ru.otus.spring.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.model.Books;
import ru.otus.spring.model.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class GenreDaoJdbc implements GenreDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public GenreDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public int count() {
        Integer count = namedParameterJdbcOperations.getJdbcOperations().queryForObject("select count(*) from Genre", Integer.class);
        return count == null ? 0 : count;
    }

    @Override
    public Genre getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject("select a.id genre_id , a.name genre_name, " +
                "b.id books_i, b.name books_name, b.genre_id , b.authors_id from Genre a  " +
                "LEFT JOIN books b ON b.id= a.id  where a.id = :id", params, new AutorsMapper());
    }

    @Override
    public List<Genre> getAll() {
        return namedParameterJdbcOperations.getJdbcOperations().query("select a.id genre_id , a.name genre_name, " +
                "b.id books_i, b.name books_name, b.genre_id , b.authors_id from Genre a  " +
                "LEFT JOIN books b ON b.id= a.id ", new AutorsMapper());
    }


    private static class AutorsMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("genre_id");
            String name = resultSet.getString("genre_name");

            long bId = resultSet.getLong("books_i");
            String bName = resultSet.getString("books_name");
            Integer bGenreId = resultSet.getInt("genre_id");
            Integer bAuthorsId = resultSet.getInt("authors_id");

            return new Genre(id, name, new Books(bId, bName, bGenreId, bAuthorsId));
        }
    }

}
