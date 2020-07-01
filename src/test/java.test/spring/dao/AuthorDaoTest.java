package spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import spring.model.Author;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ComponentScan("src.main.java.spring")
@Import(AuthorDaoImpl.class)
public class AuthorDaoTest {

    @Autowired
    private AuthorDaoImpl authorDao;

    @Test
    void findAll_correctResult() {

        List<Author> result = authorDao.findAll();

        assertEquals(3, result.size());
    }

    @Test
    void findById_correctResult() {

        Optional<Author> result = authorDao.findById(1L);

        assertEquals("Lukyanenko", result.get().getLastname());
    }

    @Test
    void findByName_correctResult() {

        Optional<Author> result = authorDao.findByName("Isaev");

        assertEquals("Isaev", result.get().getLastname());
    }

    @DisplayName(" должен сохранять информацию о нужном author")
    @Test
    void save_shouldSaveAuthor() {
        Author author = authorDao.save(new Author("firstname","lastname"));
        assertEquals("firstname", author.getFirstname());
        assertEquals("lastname", author.getLastname());
    }
}


