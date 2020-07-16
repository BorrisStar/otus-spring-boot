package spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.dao.AuthorDao;
import spring.model.Author;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorDaoService {

    private final AuthorDao authorDao;

    public AuthorDaoService(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    public Optional<Author> findByName(String lastname) {
        return authorDao.findByName(lastname);
    }

    public Optional<Author> findById(long id) {
        return authorDao.findById(id);
    }

    public List<Author> findAll() {
        return authorDao.findAll();
    }

    @Transactional
    public void delete(Author object) {
        authorDao.delete(object);
    }

    @Transactional
    public Author save(Author object) {
        return authorDao.save(object);
    }
}
