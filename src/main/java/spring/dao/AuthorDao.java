package spring.dao;

import org.springframework.data.repository.CrudRepository;
import spring.model.Author;

import java.util.Optional;

public interface AuthorDao extends CrudRepository<Author, Long> {

    Optional<Author> findByLastname(String lastname);
}
