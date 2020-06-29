package spring.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import spring.model.Book;
import spring.model.SqlQuery;

import java.sql.ResultSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spring.service.SqlService.getNamedParameters;
import static spring.service.SqlService.getParameters;
import static spring.service.SqlService.prepareSqlQueryForDelete;
import static spring.service.SqlService.prepareSqlQueryForFindAll;
import static spring.service.SqlService.prepareSqlQueryForFindById;
import static spring.service.SqlService.prepareSqlQueryForInsert;
import static spring.service.SqlService.prepareSqlQueryForUpdate;

@Repository
public class BookDaoImpl implements BookDao {
    private static String TABLE_NAME = "book";
    private static String[] TABLE_COLUMNS = {"title", "author", "genre", "year"};

    private NamedParameterJdbcTemplate jdbcTemplate;

    public BookDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Iterable<Book> findByTitle(String title) {
        String param = getParameters(title);
        return jdbcTemplate.query(SqlQuery.SQL_QUERY_FIND_BY_TITLE, getNamedParameters("title", param), bookRowMapper);
    }

    @Override
    public Iterable<Book> findByAuthor(String author) {
        String param = getParameters(author);
        return jdbcTemplate.query(SqlQuery.SQL_QUERY_FIND_BY_AUTHOR, getNamedParameters("name", param), bookRowMapper);
    }

    @Override
    public Iterable<Book> findByGenre(String genre) {
        String param = getParameters(genre);
        return jdbcTemplate.query(SqlQuery.SQL_QUERY_FIND_BY_GENRE, getNamedParameters("genre", param), bookRowMapper);
    }

    @Override
    public List<Map<String, Object>> findAllWithAddInfo() {
        return jdbcTemplate.queryForList(SqlQuery.SQL_QUERY_FIND_ALL_WITH_ADD_INFO, new HashMap<>());
    }

    @Override
    public Book save(Book domain) {
        Book result = findById(domain.getId());
        if (result != null) {
            result.setTitle(domain.getTitle());
            result.setAuthor(domain.getAuthor());
            result.setGenre(domain.getGenre());
            result.setYear(domain.getYear());
            return createOrUpdate(result, prepareSqlQueryForUpdate(TABLE_NAME, TABLE_COLUMNS));
        }
        return createOrUpdate(domain, prepareSqlQueryForInsert(TABLE_NAME, TABLE_COLUMNS));
    }

    @Override
    public Iterable<Book> save(Collection<Book> domains) {
        domains.forEach(this::save);
        return findAll();
    }

    @Override
    public void delete(Book domain) {
        jdbcTemplate.update(prepareSqlQueryForDelete(TABLE_NAME), getNamedParameters("id", domain.getId()));
    }

    @Override
    public Book findById(long id) {
        try {
            return jdbcTemplate.queryForObject(prepareSqlQueryForFindById(TABLE_NAME), getNamedParameters("id", id), bookRowMapper);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public Iterable<Book> findAll() {
        return jdbcTemplate.query(prepareSqlQueryForFindAll(TABLE_NAME), bookRowMapper);
    }

    private RowMapper<Book> bookRowMapper = (ResultSet rs, int rowNum) -> {
        Book book = new Book();
        book.setId(rs.getLong("id"));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getLong("author"));
        book.setGenre(rs.getLong("genre"));
        book.setYear(rs.getString("year"));
        return book;
    };

    private Book createOrUpdate(Book book, String sql) {
        long bookId = book.getId();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("id", bookId)
                .addValue("title", book.getTitle())
                .addValue("author", book.getAuthor())
                .addValue("genre", book.getGenre())
                .addValue("year", book.getYear());
        jdbcTemplate.update(sql, sqlParameterSource, keyHolder);
        Number number = keyHolder.getKey();
        if (number == null) {
            return findById(book.getId());
        } else {
            return findById(number.longValue());
        }
    }
}
