package spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import spring.model.Book;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Репозиторий для работы с book")
@DataJpaTest
@Import(BookDaoImpl.class)
public class BookDaoTest {

    @Autowired
    private BookDaoImpl bookDao;

    @DisplayName(" должен загружать информацию о нужном book по его id")
    @Test
    void findById_shouldFindExpectedBook() {
        Book book = bookDao.findById(1);
        assertEquals(new Book("the night Watch", 1, 2, "1995"), book);
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
