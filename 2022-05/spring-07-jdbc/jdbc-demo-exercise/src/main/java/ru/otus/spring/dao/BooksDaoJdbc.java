package ru.otus.spring.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.model.Books;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class BooksDaoJdbc implements BooksDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public BooksDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public int count() {
        Integer count = namedParameterJdbcOperations.getJdbcOperations().queryForObject("select count(*) from books", Integer.class);
        return count == null ? 0 : count;
    }

    @Override
    public void insert(Books books) {
        Integer seq = namedParameterJdbcOperations.getJdbcOperations().queryForObject("select NEXT VALUE FOR SEQ_ID from dual", Integer.class);
        books.setId(seq);
        namedParameterJdbcOperations.update("insert into books (id, name, genre_id, authors_id) values (:id, :name, :genreId, :authorsId)",
                Map.of("id", seq, "name", books.getName(), "genreId", books.getGenreId(), "authorsId", books.getAuthorsId()));
    }

    @Override
    public Books getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select b.id, b.name, b.genre_id, b.authors_id    " +
                        "from books b where b.id = :id", params, new BooksMapper()
        );
    }

    @Override
    public List<Books> getAll() {
        return namedParameterJdbcOperations.getJdbcOperations().query("select b.id, b.name, b.genre_id, b.authors_id from books b", new BooksMapper());
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from books where id = :id", params
        );
    }

    @Override
    public void update(Books books) {
        namedParameterJdbcOperations.update(
                "update books set name = :name, genre_id = :genreId, authors_id =:authorsId where id = :id",
                Map.of("id", books.getId(), "name", books.getName(), "genreId", books.getGenreId(), "authorsId", books.getAuthorsId()));
    }

    private static class BooksMapper implements RowMapper<Books> {
        @Override
        public Books mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            Integer genreId = resultSet.getInt("genre_id");
            Integer authorsId = resultSet.getInt("authors_id");
            return new Books(id, name, genreId, authorsId);
        }
    }
}
