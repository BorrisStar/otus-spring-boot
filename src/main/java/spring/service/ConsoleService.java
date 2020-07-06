package spring.service;

import org.h2.tools.Console;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Service;
import spring.model.Book;
import spring.model.Comment;
import spring.model.Genre;

import java.sql.SQLException;
import java.util.Optional;

@Service
@ShellComponent
public class ConsoleService {

    private final AuthorDaoService authorDaoService;
    private final BookDaoService bookDaoService;
    private final GenreDaoService genreDaoService;
    private final CommentDaoService commentDaoService;

    public ConsoleService(AuthorDaoService authorDaoService, BookDaoService bookDaoService, GenreDaoService genreDaoService, CommentDaoService commentDaoService) {
        this.authorDaoService = authorDaoService;
        this.bookDaoService = bookDaoService;
        this.genreDaoService = genreDaoService;
        this.commentDaoService = commentDaoService;
    }

    @ShellMethod(key = "show comments for book", value = "Show comments for book")
    public void showCommentsForBook(Book book) {
        commentDaoService.findAllForBook(book).forEach(g -> System.out.println(g.toString()));
    }

    @ShellMethod(key = "show comments", value = "Show comments")
    public void showComments() {
        commentDaoService.findAll().forEach(g -> System.out.println(g.toString()));
    }

    @ShellMethod(key = "save comment", value = "Save comment")
    public void saveComment(Comment newComment) {
        commentDaoService.save(newComment);
    }

    @ShellMethod(key = "delete comment", value = "Delete comment")
    public void deleteComment(Comment comment) {
        commentDaoService.save(comment);
    }

    @ShellMethod(key = "show authors", value = "Show all authors.")
    public void displayAuthors() {
        authorDaoService.findAll().forEach(g -> System.out.println(g.toString()));
    }

    @ShellMethod(key = "show books", value = "Show all books.")
    public void displayBooksWithDetails() {
        bookDaoService.findAll().forEach(it -> System.out.println(it.toString()));
    }

    @ShellMethod(key = "show genres", value = "Show all genres")
    public void displayGenres() {
        genreDaoService.findAll().forEach(g -> System.out.println(g.toString()));
    }

    @ShellMethod(key = "genre by id",value = "Find genre by id")
    public void findGenreById( long id) {
        System.out.println(genreDaoService.findById(id));
    }

    @ShellMethod(key = "genre by name", value = "Find genre by name")
    public void findGenreByName( String genre) {
        System.out.println(genreDaoService.findByGenre(genre));
    }

    @ShellMethod(key = "delete genre", value = "Delete genre by name")
    public void deleteGenreByName( String name) {
         Optional<Genre> genre = genreDaoService.findByGenre(name);
        if (genre.isPresent()) {
            genreDaoService.delete(genre.get());
        } else {
            System.out.println(name + " not found");
        }
    }

    @ShellMethod(key = "save genre", value = "Save genre")
    public void saveGenre( Genre genre) {
        genreDaoService.save(genre);
    }


    @ShellMethod(key = "run h2console", value = "Start H2 console")
    public void dbConsole() throws SQLException {
        Console.main();
    }
}
