package spring.service;

import org.springframework.stereotype.Service;
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

    public List<Author> findAll() {
        return (List<Author>) authorDao.findAll();
    }

    public Optional<Author> findByLastname(String lastname) {
        return authorDao.findByLastname(lastname);
    }
}
