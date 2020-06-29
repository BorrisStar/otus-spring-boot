package spring.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import spring.model.Author;
import spring.model.SqlQuery;

import java.sql.ResultSet;
import java.util.Collection;

import static spring.service.SqlService.getNamedParameters;
import static spring.service.SqlService.getParameters;
import static spring.service.SqlService.prepareSqlQueryForDelete;
import static spring.service.SqlService.prepareSqlQueryForFindAll;
import static spring.service.SqlService.prepareSqlQueryForFindById;
import static spring.service.SqlService.prepareSqlQueryForInsert;
import static spring.service.SqlService.prepareSqlQueryForUpdate;

@Repository
public class AuthorDaoImpl implements AuthorDao {

    private static String TABLE_NAME = "author";
    private static String[] TABLE_COLUMNS = {"firstname", "lastname"};

    private NamedParameterJdbcTemplate jdbcTemplate;

    public AuthorDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Author save(Author domain) {
        Author result = findById(domain.getId());
        if (result != null) {
            result.setFirstName(domain.getFirstName());
            result.setLastName(domain.getLastName());
            return createOrUpdate(result, prepareSqlQueryForUpdate(TABLE_NAME, TABLE_COLUMNS));
        }
        return createOrUpdate(domain, prepareSqlQueryForInsert(TABLE_NAME, TABLE_COLUMNS));
    }

    @Override
    public Iterable<Author> save(Collection<Author> domains) {
        domains.forEach(this::save);
        return findAll();
    }

    @Override
    public void delete(Author author) {
        jdbcTemplate.update(prepareSqlQueryForDelete(TABLE_NAME), getNamedParameters("id", author.getId()));
    }

    @Override
    public Author findById(long id) {
        try {
            return jdbcTemplate.queryForObject(prepareSqlQueryForFindById(TABLE_NAME), getNamedParameters("id", id), authorRowMapper);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Iterable<Author> findAll() {
        return jdbcTemplate.query(prepareSqlQueryForFindAll(TABLE_NAME), authorRowMapper);
    }

    @Override
    public Iterable<Author> findByName(String name) {
        String param = getParameters(name);
        return jdbcTemplate.query(SqlQuery.SQL_QUERY_FIND_AUTHOR_BY_NAME, getNamedParameters("name", param), authorRowMapper);
    }

    private RowMapper<Author> authorRowMapper = (ResultSet rs, int rowNum) -> {
        Author author = new Author();
        author.setId(rs.getLong("id"));
        author.setFirstName(rs.getString("firstname"));
        author.setLastName(rs.getString("lastname"));
        return author;
    };

    private Author createOrUpdate(Author author, String sql) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("id", author.getId())
                .addValue("firstname", author.getFirstName())
                .addValue("lastname", author.getLastName());
        jdbcTemplate.update(sql, sqlParameterSource, keyHolder);
        Number number = keyHolder.getKey();
        if (number == null) {
            return findById(author.getId());
        } else {
            return findById(number.longValue());
        }
    }
}

