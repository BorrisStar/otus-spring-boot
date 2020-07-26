package spring.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import spring.model.Book;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
@EnableConfigurationProperties
@ComponentScan("src.main.java.spring")
public class BookDaoServiceTest {
    @Autowired
    private BookDaoService BookDaoService;
    @Test
    void findAllByBook_correctResult() {
        List<Book> result = BookDaoService.findAll();

        assertEquals(4, result.size());
    }
}
