package spring.dao;

import spring.model.Genre;

import java.util.Optional;

public interface GenreDao extends StoreRepository<Genre>{
    Optional<Genre> findByGenre(String genre);
}
