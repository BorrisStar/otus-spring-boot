package spring.service;


import org.springframework.stereotype.Service;
import spring.dao.BookDao;
import spring.model.Book;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BookDaoService {
    private final BookDao bookDao;

    public BookDaoService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public Optional<Book> findById(long id) {
        return bookDao.findById(id);
    }
    public Optional<Book> findByTitle(String title){
        return bookDao.findByTitle(title);
    }

    public List<Book> findBooksByAuthor(long author){
        return bookDao.findBooksByAuthor(author);
    }

    public List<Book> findAll() {
        return (List<Book>) bookDao.findAll();
    }
    @Transactional
    public void deleteBookByTitle(String title){
        bookDao.deleteBookByTitle(title);
    }
    @Transactional
    public void deleteBookById(Long id){
        bookDao.deleteBookById(id);
    }
    @Transactional
    public Book save(Book book){
        return bookDao.save(book);
    }
}
