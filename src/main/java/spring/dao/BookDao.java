package spring.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import spring.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao extends MongoRepository<Book, Long> {

    Optional<Book> findByTitle(String title);
    List<Book> findBooksByAuthor(long author);
}
