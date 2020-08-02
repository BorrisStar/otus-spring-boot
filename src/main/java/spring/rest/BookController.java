package spring.rest;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.dto.BookDto;
import spring.exception.NotFoundException;
import spring.model.Author;
import spring.model.Book;
import spring.model.Genre;
import spring.service.AuthorDaoService;
import spring.service.BookDaoService;
import spring.service.GenreDaoService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("books")
public class BookController {
    private final AuthorDaoService authorDaoService;
    private final BookDaoService bookDaoService;
    private final GenreDaoService genreDaoService;

    @GetMapping("/all")
    public String getAll(Model model) {
        List<Book> bookList = bookDaoService.findAll();
        List<BookDto> books = bookList.stream().map(this::toBookDto).collect(Collectors.toList());
        model.addAttribute("books", books);
        return "list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id, Model model) {
        //Можно сначала проверить наличие Book с таким title
        bookDaoService.deleteBookById(id);
        return getAll(model);
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam("id") Long id, Model model) {
        Book book = bookDaoService.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("book", book);
        return "edit";
    }

    @PostMapping("/edit")
    public String update(BookDto bookDto, Model model) {
        Book book = toBook(bookDto);
        bookDaoService.save(book);
        return getAll(model);
    }

    private BookDto toBookDto(Book book) {
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

    private Book toBook(BookDto bookDto) {
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
