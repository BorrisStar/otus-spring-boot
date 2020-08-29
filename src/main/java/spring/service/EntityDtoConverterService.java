package spring.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spring.dto.BookDto;
import spring.model.Author;
import spring.model.Book;
import spring.model.Genre;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EntityDtoConverterService {

    private final AuthorDaoService authorDaoService;
    private final GenreDaoService genreDaoService;


    public BookDto toBookDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());

        Optional<Author> author = authorDaoService.findById(book.getAuthor());
        author.ifPresent(value -> bookDto.setAuthor(value.getFirstname() + " " + value.getLastname()));

        Optional<Genre> genre = genreDaoService.findById(book.getGenre());
        genre.ifPresent(value -> bookDto.setGenre(value.getGenre()));

        bookDto.setYear(book.getYear());
        return bookDto;
    }


    public Book toBook(BookDto bookDto) {
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setTitle(bookDto.getTitle());

        String[] authorName = bookDto.getAuthor().split(" ");
        Optional<Author> author = authorDaoService.findByLastname(authorName[1]);
        author.ifPresent(value -> book.setAuthor(value.getId()));

        Optional<Genre> genre = genreDaoService.findByGenre(bookDto.getGenre());
        genre.ifPresent(value -> book.setGenre(value.getId()));

        book.setYear(bookDto.getYear());
        return book;
    }
}
