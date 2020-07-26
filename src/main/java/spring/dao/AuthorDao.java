package spring.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import spring.model.Author;

import java.util.Optional;

public interface AuthorDao extends MongoRepository<Author, Long> {

    Optional<Author> findByLastname(String lastname);
}
