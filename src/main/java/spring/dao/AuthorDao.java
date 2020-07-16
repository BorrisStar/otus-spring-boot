package spring.dao;

import spring.model.Author;

import java.util.Optional;

public interface AuthorDao extends StoreRepository<Author>{

    Optional<Author> findByName(String lastname);
}
