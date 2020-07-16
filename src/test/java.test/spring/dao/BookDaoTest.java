package spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import spring.model.Book;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ComponentScan("src.main.java.spring")
@Import({BookDaoImpl.class, AuthorDaoImpl.class, GenreDaoImpl.class})
public class BookDaoTest {

    @Autowired
    private BookDaoImpl bookDao;

    @Test
    void findAll_correctResult() {

        List<Book> result = bookDao.findAll();

        assertEquals(4, result.size());
    }

    @Test
    void findById_correctResult() {

        Optional<Book> result = bookDao.findById(1L);

        assertEquals("the night Watch", result.get().getTitle());
    }

    @Test
    void findByTitle_correctResult() {

        Optional<Book> result = bookDao.findByTitle("the night Watch");

        assertEquals("the night Watch", result.get().getTitle());
    }

    @Test
    void findByAuthor_correctResult() {

        List<Book> result = bookDao.findByAuthor("Isaev");

        assertEquals(2, result.size());
    }

    @Test
    void findByGenre_correctResult() {

        List<Book> result = bookDao.findByGenre("Military");

        assertEquals(2, result.size());
    }

    @DisplayName(" должен сохранять информацию о нужном book")
    @Test
    void save_shouldSaveBook() {
        Book book = bookDao.save(new Book("New Book", 1, 1, "0000"));
        assertEquals("New Book", book.getTitle());
        assertEquals(1, book.getAuthor());
        assertEquals(1, book.getGenre());
    }
}


