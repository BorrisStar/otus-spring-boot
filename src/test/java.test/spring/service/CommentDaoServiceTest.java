package spring.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import spring.dao.AuthorDaoImpl;
import spring.dao.BookDaoImpl;
import spring.dao.CommentDaoImpl;
import spring.dao.GenreDaoImpl;
import spring.model.Comment;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ComponentScan("src.main.java.spring")
@Import({CommentDaoImpl.class, CommentDaoService.class, AuthorDaoImpl.class, BookDaoImpl.class, GenreDaoImpl.class})
public class CommentDaoServiceTest {

    @Autowired
    private CommentDaoService commentDaoService;

    @Test
    void findAllByBook_correctResult() {
        List<Comment> result = commentDaoService.findAllForBook(1L);

        assertEquals(2, result.size());
    }
}