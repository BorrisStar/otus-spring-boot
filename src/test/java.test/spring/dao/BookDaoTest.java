package spring.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import spring.model.Book;

import java.util.ArrayList;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@EnableConfigurationProperties
@ComponentScan("src.main.java.spring")
public class BookDaoTest {

    @Autowired
    private BookDao bookDao;

    @Test
    @DirtiesContext
    void saveTest() {
        Mono<Book> bookMono = bookDao.save(
                new Book("Test book", 1L, 1L, "2020", Collections.emptyList()));

        StepVerifier
                .create(bookMono)
                .assertNext(Book::getId)
                .expectComplete()
                .verify();
    }

    @Test
    void findAllCorrectBooksTest() {
        Flux<Book> all = bookDao.findAll();

        StepVerifier
                .create(all)
                .recordWith(ArrayList::new)
                .expectNextCount(5)
                .consumeRecordedWith(results -> {
                    assertThat(results).extracting(Book::getTitle).contains("Test book 1", "Test book 2", "Test book 3", "Test book 4", "Test book 5");
                })
                .verifyComplete();
    }

    @Test
    void findAllBooksTest() {
        StepVerifier.create(bookDao.findAll())
                .expectNextCount(5)
                .verifyComplete();
    }
}
