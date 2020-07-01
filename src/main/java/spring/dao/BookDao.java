package spring.dao;

import spring.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao extends StoreRepository<Book>{

    Optional<Book> findByTitle(String title);

    List<Book> findByAuthor(String author);

    List<Book> findByGenre(String genre);
}
