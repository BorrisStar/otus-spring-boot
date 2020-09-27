package spring.rest;

import lombok.AllArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.dto.BookDto;
import spring.exception.NotFoundException;
import spring.model.Book;
import spring.model.BookResponseBody;
import spring.model.BooksResponseBody;
import spring.model.SearchQuery;
import spring.service.BookDaoService;
import spring.service.EntityDtoConverterService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/books")
public class BookController {
    private final BookDaoService bookDaoService;
    private final EntityDtoConverterService entityDtoConverterService;

    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        BooksResponseBody result = new BooksResponseBody();

        List<Book> bookList = bookDaoService.findAll();
        List<BookDto> books = bookList.stream().map(entityDtoConverterService::toBookDto).collect(Collectors.toList());
        if (books.isEmpty()) {
            result.setMsg("no books found!");
        } else {
            result.setMsg("success");
        }

        result.setResult(books);

        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam("id") Long id) {
        //Можно сначала проверить наличие Book с таким title
        bookDaoService.deleteBookById(id);
        return ResponseEntity.ok("Book with id:"+ id + " deleted");
    }

    @PostMapping("/search")
    public ResponseEntity<?> searchBookById(@RequestBody SearchQuery searchQuery, Errors errors) {
        BookResponseBody result = new BookResponseBody();
        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

            result.setMsg(errors.getAllErrors()
                    .stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(",")));

            return ResponseEntity.badRequest().body(result);

        }
        Book book = bookDaoService.findById(searchQuery.getBookId()).orElseThrow(NotFoundException::new);

        if (book == null) {
            result.setMsg("no book found!");
        } else {
            result.setMsg("success");
            result.setBookDto(entityDtoConverterService.toBookDto(book));
        }

        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/edit")
    public ResponseEntity<?> update(BookDto bookDto) {
        Book book = entityDtoConverterService.toBook(bookDto);
        bookDaoService.save(book);
        return ResponseEntity.ok(book);
    }
}
