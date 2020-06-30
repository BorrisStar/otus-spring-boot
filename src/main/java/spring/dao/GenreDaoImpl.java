package spring.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import spring.model.Genre;
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
public class GenreDaoImpl implements GenreDao {
    private static String TABLE_NAME = "genre";
    private static String[] TABLE_COLUMNS = {"genre"};

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public GenreDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Genre save(Genre genre) {
        Genre result = findById(genre.getId());
        if (result != null) {
            result.setGenre(genre.getGenre());
            return createOrUpdate(result, prepareSqlQueryForUpdate(TABLE_NAME, TABLE_COLUMNS));
        }
        return createOrUpdate(genre, prepareSqlQueryForInsert(TABLE_NAME, TABLE_COLUMNS));
    }

    @Override
    public Iterable<Genre> save(Collection<Genre> genres) {
        genres.forEach(this::save);
        return findAll();
    }

    @Override
    public void delete(Genre domain) {
        namedParameterJdbcTemplate.update(prepareSqlQueryForDelete(TABLE_NAME), getNamedParameters("id", domain.getId()));
    }

    @Override
    public Genre findById(long id) {
        try {
            return namedParameterJdbcTemplate.queryForObject(prepareSqlQueryForFindById(TABLE_NAME), getNamedParameters("id", id), genreRowMapper);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Genre findByGenre(String genre) {
        try {
            String param = getParameters(genre);
            return namedParameterJdbcTemplate.queryForObject(SqlQuery.SQL_QUERY_FIND_GENRE_BY_NAME, getNamedParameters("genre", param), genreRowMapper);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Iterable<Genre> findAll() {
        return namedParameterJdbcTemplate.query(prepareSqlQueryForFindAll(TABLE_NAME), genreRowMapper);
    }

    private RowMapper<Genre> genreRowMapper = (ResultSet rs, int rowNum) -> {
        Genre genre = new Genre();
        genre.setId(rs.getLong("id"));
        genre.setGenre(rs.getString("genre"));
        return genre;
    };

    private Genre createOrUpdate(Genre genre, String sql) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("id", genre.getId())
                .addValue("genre", genre.getGenre());
        namedParameterJdbcTemplate.update(sql, sqlParameterSource, keyHolder);
        Number number = keyHolder.getKey();
        if (number == null) {
            return findById(genre.getId());
        } else {
            return findById(number.longValue());
        }
    }
}
