package spring.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import spring.model.Book;

public interface BookDao extends MongoRepository<Book, Long> {

}
