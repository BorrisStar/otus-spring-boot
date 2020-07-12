package spring.dao;

import org.springframework.data.repository.CrudRepository;
import spring.model.Genre;

import java.util.Optional;

public interface GenreDao extends CrudRepository<Genre, Long> {
    Optional<Genre> findByGenre(String genre);
}
