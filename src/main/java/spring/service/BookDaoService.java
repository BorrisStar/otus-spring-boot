package spring.service;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import spring.dao.BookDao;
import spring.model.Book;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BookDaoService {
    private final BookDao bookDao;
    private final EmergencyBookService emergencyBookService;

    public BookDaoService(BookDao bookDao, EmergencyBookService emergencyBookService) {
        this.bookDao = bookDao;
        this.emergencyBookService = emergencyBookService;
    }
    @HystrixCommand(groupKey = "BookDao", fallbackMethod = "getBookById", commandKey = "findBookById")
    public Optional<Book> findById(long id) {
        return bookDao.findById(id);
    }

    @HystrixCommand(groupKey = "BookDao", fallbackMethod = "getAllBooks", commandKey = "findAllBooks")
    public List<Book> findAll() {
        return (List<Book>) bookDao.findAll();
    }

    private List<Book> getAllBooks() {
        return emergencyBookService.getDefaultBooks();
    }

    private Optional<Book> getBookById(long id) {
        return emergencyBookService.getDefaultBook();
    }



    public Optional<Book> findByTitle(String title){
        return bookDao.findByTitle(title);
    }

    public List<Book> findBooksByAuthor(long author){
        return bookDao.findBooksByAuthor(author);
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
