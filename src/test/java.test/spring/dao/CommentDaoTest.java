package spring.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import spring.model.Comment;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ComponentScan("src.main.java.spring")
@Import({CommentDaoImpl.class, AuthorDaoImpl.class, BookDaoImpl.class, GenreDaoImpl.class})
public class CommentDaoTest {

    @Autowired
    private BookDaoImpl bookDao;

    @Autowired
    private CommentDaoImpl commentDao;

    @Test
    void findAll_correctResult() {

        List<Comment> result = commentDao.findAll();

        assertEquals(4, result.size());
    }

    @Test
    void findById_correctResult() {

        Optional<Comment> result = commentDao.findById(1L);

        assertEquals(1L, result.get().getId());
    }

    @Test
    void findByName_correctResult() {

        Optional<Comment> result = commentDao.findById(1);

        assertEquals("the night Watch", result.get().getBook().getTitle());
        assertEquals("Unbelievable!", result.get().getText());
    }
}


