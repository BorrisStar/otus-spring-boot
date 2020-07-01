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

    @Transactional(readOnly = true)
    public Optional<Genre> findByGenre(String genre) {
        return genreDao.findByGenre(genre);
    }

    @Transactional(readOnly = true)
    public Optional<Genre> findById(long id) {
        return genreDao.findById(id);
    }

    @Transactional()
    public void delete(Genre object) {
        genreDao.delete(object);
    }

    @Transactional(readOnly = true)
    public List<Genre> findAll() {
        return genreDao.findAll();
    }

    @Transactional()
    public Genre save(Genre object) {
        return genreDao.save(object);
    }
}
