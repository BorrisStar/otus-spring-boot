package spring.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import spring.model.Comment;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
@EnableConfigurationProperties
@ComponentScan("src.main.java.spring")
public class CommentDaoServiceTest {

    @Autowired
    private CommentDaoService commentDaoService;

    @Test
    void findAllByBook_correctResult() {
        List<Comment> result = commentDaoService.findAllForBook(1L);

        assertEquals(2, result.size());
    }
}