package ru.otus.spring.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.model.Authors;
import ru.otus.spring.model.Books;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class AuthorsDaoJdbc implements AuthorsDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public AuthorsDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public int count() {
        Integer count = namedParameterJdbcOperations.getJdbcOperations().queryForObject("select count(*) from authors", Integer.class);
        return count == null ? 0 : count;
    }


    @Override
    public Authors getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject("select a.id authors_id , a.name authors_name, a.surname authors_surname, " +
                "b.id books_i, b.name books_name, b.genre_id , b.authors_id from authors a  " +
                        "LEFT JOIN books b ON b.id= a.id  where a.id = :id", params, new AutorsMapper() );
    }

    @Override
    public List<Authors> getAll() {
        return namedParameterJdbcOperations.getJdbcOperations().query("select a.id authors_id , a.name authors_name, a.surname authors_surname, " +
                "b.id books_i, b.name books_name, b.genre_id , b.authors_id from authors a  " +
                "LEFT JOIN books b ON b.id= a.id ", new AutorsMapper());
    }


    private static class AutorsMapper implements RowMapper<Authors> {
        @Override
        public Authors mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("authors_id");
            String name = resultSet.getString("authors_name");
            String surname = resultSet.getString("authors_surname");

            long bId = resultSet.getLong("books_i");
            String bName = resultSet.getString("books_name");
            Integer bGenreId = resultSet.getInt("genre_id");
            Integer bAuthorsId = resultSet.getInt("authors_id");
            return new Authors(id, name, surname, new Books(bId,bName,bGenreId,bAuthorsId));
        }
    }

}
