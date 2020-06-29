package spring.dao;

import spring.model.Author;

public interface AuthorDao extends StoreRepository<Author>{

    Iterable<Author> findByName(String name);
}
