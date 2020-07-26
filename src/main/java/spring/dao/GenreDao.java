package spring.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import spring.model.Genre;

import java.util.Optional;

public interface GenreDao extends MongoRepository<Genre, Long> {
    Optional<Genre> findByGenre(String genre);
}
