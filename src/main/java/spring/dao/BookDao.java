package spring.dao;

import org.springframework.data.repository.CrudRepository;
import spring.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao extends CrudRepository<Book, Long> {

    Optional<Book> findByTitle(String title);
    List<Book> findBooksByAuthor(long author);
}
