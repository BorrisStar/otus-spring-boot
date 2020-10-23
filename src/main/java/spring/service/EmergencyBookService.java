package spring.service;

import org.springframework.stereotype.Service;
import spring.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmergencyBookService {

    public List<Book> getDefaultBooks() {
        List<Book> books = new ArrayList<>();
        books.add(defaultBook());
        return books;
    }

    public Optional<Book> getDefaultBook() {
        return Optional.of(defaultBook());
    }

    private Book defaultBook() {
        return new Book("Empty Title", 1L, 1L, "2020");
    }
}
