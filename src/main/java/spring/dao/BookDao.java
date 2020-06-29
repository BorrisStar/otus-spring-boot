package spring.dao;

import spring.model.Book;

import java.util.List;
import java.util.Map;

public interface BookDao extends StoreRepository<Book>{

    List<Map<String, Object>> findAllWithAddInfo();

    Iterable<Book> findByTitle(String title);

    Iterable<Book> findByAuthor(String author);

    Iterable<Book> findByGenre(String genre);


}
