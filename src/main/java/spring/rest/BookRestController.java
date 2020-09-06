package spring.rest;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.dao.BookDao;
import spring.model.Book;

@RestController
@CrossOrigin
@RequestMapping("/")
@RequiredArgsConstructor
public class BookRestController {

    private final BookDao bookDao;

    @GetMapping("/books")
    public Flux<Book> readAll() {
        return bookDao.findAll();
    }

    @GetMapping("/books/{id}")
    public Mono<Book> read(@PathVariable("id") Long id) {
        return bookDao.findById(id);
    }

    @GetMapping("/books/author/{id}")
    public Flux<Book> readByAuthor(@PathVariable("id") Long id) {
        return bookDao.findBooksByAuthor(id);
    }

    @PostMapping("/books")
    public Mono<Book> create(@RequestBody Book book) {
        return bookDao.save(book);
    }

    @PutMapping("/books/{id}")
    public Mono<Book> update(@RequestBody Book book) {
        return bookDao.save(book);
    }

    @DeleteMapping("/books/{id}")
    public Mono<Void> delete(@PathVariable("id") Long id) {
        return bookDao.deleteById(id);
    }

}
