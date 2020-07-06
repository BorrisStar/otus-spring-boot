package spring.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.dao.BookDao;
import spring.model.Book;

import java.util.List;
import java.util.Optional;

@Service
public class BookDaoService {
    private final BookDao bookDao;

    public BookDaoService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public Optional<Book> findByTitle(String title) {
        return bookDao.findByTitle(title);
    }

    public List<Book> findByAuthor(String lastname) {
        return bookDao.findByAuthor(lastname);
    }

    public List<Book> findByGenre(String genre) {
        return bookDao.findByGenre(genre);
    }

    public Optional<Book> findById(long id) {
        return bookDao.findById(id);
    }

    @Transactional
    public void delete(Book object) {
        bookDao.delete(object);
    }

    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Transactional
    public Book save(Book object) {
        return bookDao.save(object);
    }
}
