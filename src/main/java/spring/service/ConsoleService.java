package spring.service;

import org.springframework.stereotype.Service;
import spring.model.Author;
import spring.model.Book;
import spring.model.Comment;
import spring.model.Genre;

import java.util.Optional;

@Service
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

    public void showCommentsForBook(String title) {
        Optional<Book> book = bookDaoService.findByTitle(title);
        book.ifPresent(value -> commentDaoService.findAllForBook(value.getId()).forEach(g -> System.out.println(g.toString())));
    }

    public void showComments() {
        commentDaoService.findAll().forEach(g -> System.out.println(g.toString()));
    }

    public void saveComment(Comment newComment) {
        commentDaoService.save(newComment);
    }

    public void deleteComment(Comment comment) {
        commentDaoService.save(comment);
    }

    public void displayAuthors() {
        authorDaoService.findAll().forEach(g -> System.out.println(g.toString()));
    }

    public void displayBooksWithDetails() {
        bookDaoService.findAll().forEach(it -> System.out.println(it.toString()));
    }

    public void displayGenres() {
        genreDaoService.findAll().forEach(g -> System.out.println(g.toString()));
    }

    public void findGenreById( long id) {
        System.out.println(genreDaoService.findById(id));
    }

    public void findGenreByName( String genre) {
        System.out.println(genreDaoService.findByGenre(genre));
    }

    public void showBooks(String lastname) {
        Optional<Author> author = authorDaoService.findByLastname(lastname);
        author.ifPresent(value -> bookDaoService.findBooksByAuthor(value.getId()).forEach(g -> System.out.println(g.toString())));
    }

    public void deleteGenreByName( String name) {
         Optional<Genre> genre = genreDaoService.findByGenre(name);
        if (genre.isPresent()) {
            genreDaoService.delete(genre.get());
        } else {
            System.out.println(name + " not found");
        }
    }

    public void saveGenre( Genre genre) {
        genreDaoService.save(genre);
    }

}
