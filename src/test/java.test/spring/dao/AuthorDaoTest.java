package spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import spring.model.Author;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Репозиторий для работы с author")
@DataJpaTest
@Import(AuthorDaoImpl.class)
public class AuthorDaoTest {

    @Autowired
    private AuthorDaoImpl authorDao;

    @DisplayName(" должен загружать информацию о нужном author по его id")
    @Test
    void findById_shouldFindExpectedAuthor() {
        Author author = authorDao.findById(1);
        assertEquals(new Author(1,"Andrey","Lukyanenko"), author);
    }

    @DisplayName(" должен сохранять информацию о нужном author")
    @Test
    void save_shouldSaveAuthor() {
        Author author = authorDao.save(new Author("firstname","lastname"));
        assertEquals("firstname", author.getFirstName());
        assertEquals("lastname", author.getLastName());
    }
}
