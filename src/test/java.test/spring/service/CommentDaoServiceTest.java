package spring.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import spring.dao.AuthorDao;
import spring.dao.BookDao;
import spring.dao.CommentDao;
import spring.dao.GenreDao;
import spring.model.Comment;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ComponentScan("src.main.java.spring")
@Import({CommentDao.class, CommentDaoService.class, AuthorDao.class, BookDao.class, GenreDao.class})
public class CommentDaoServiceTest {

    @Autowired
    private CommentDaoService commentDaoService;

    @Test
    void findAllByBook_correctResult() {
        List<Comment> result = commentDaoService.findAllForBook(1L);

        assertEquals(2, result.size());
    }
}