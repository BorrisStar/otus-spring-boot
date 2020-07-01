package spring.dao;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import spring.model.Genre;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Репозиторий для работы с genre")
@DataJpaTest
@Import(GenreDaoImpl.class)
public class GenreDaoTest {
    @Autowired
    private GenreDaoImpl genreDao;

    @DisplayName(" должен загружать информацию о нужном genre по его id")
    @Test
    void findById_shouldFindExpectedGenre() {
        Genre genre = genreDao.findById(1);
        assertEquals(new Genre(1, "Military"), genre);
    }

    @DisplayName(" должен сохранять информацию о нужном genre")
    @Test
    void save_shouldSaveGenre() {
        Genre genre = genreDao.save(new Genre("Proza"));
        assertEquals("Proza", genre.getGenre());
    }
}
