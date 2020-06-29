package spring.dao;

import spring.model.Genre;

public interface GenreDao extends StoreRepository<Genre>{
    Genre findByGenre(String genre);
}
