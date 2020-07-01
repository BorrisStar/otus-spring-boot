package spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import spring.model.Genre;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ComponentScan("src.main.java.spring")
@Import(GenreDaoImpl.class)
public class GenreDaoTest {

    @Autowired
    private GenreDaoImpl genreDao;

    @Test
    void findAll_correctResult() {

        List<Genre> result = genreDao.findAll();

        assertEquals(3, result.size());
    }

    @Test
    void findById_correctResult() {

        Optional<Genre> result = genreDao.findById(1L);

        assertEquals("Military", result.get().getGenre());
    }

    @Test
    void findByName_correctResult() {

        Optional<Genre> result = genreDao.findByGenre("Military");

        assertEquals(1, result.get().getId());
    }

    @DisplayName(" должен сохранять информацию о нужном genre")
    @Test
    void save_shouldSaveGenre() {
        Genre genre = genreDao.save(new Genre("Proza"));
        assertEquals("Proza", genre.getGenre());
    }
}


