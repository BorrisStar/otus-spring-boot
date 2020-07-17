package spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.dao.GenreDao;
import spring.model.Genre;

import java.util.List;
import java.util.Optional;

@Service
public class GenreDaoService {

    private final GenreDao genreDao;

    public GenreDaoService(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    public Optional<Genre> findByGenre(String genre) {
        return genreDao.findByGenre(genre);
    }

    public Optional<Genre> findById(long id) {
        return genreDao.findById(id);
    }

    @Transactional()
    public void delete(Genre object) {
        genreDao.delete(object);
    }

    public List<Genre> findAll() {
        return (List<Genre>) genreDao.findAll();
    }

    @Transactional()
    public void save(Genre object) {
        genreDao.save(object);
    }
}
