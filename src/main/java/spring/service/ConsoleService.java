package spring.service;

import org.h2.tools.Console;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Service;
import spring.dao.AuthorDao;
import spring.dao.BookDao;
import spring.dao.GenreDao;
import spring.model.Genre;

import java.sql.SQLException;

@Service
@ShellComponent
public class ConsoleService {

    private  AuthorDao authorDao;
    private  BookDao bookDao;
    private  GenreDao genreDao;

    public ConsoleService(GenreDao genreDao, AuthorDao authorDao, BookDao bookDao) {
        this.genreDao = genreDao;
        this.authorDao = authorDao;
        this.bookDao = bookDao;
    }

    @ShellMethod(key = "show authors", value = "Show all authors.")
    public void displayAuthors() {
        authorDao.findAll().forEach(g -> System.out.println(g  .toString()));
    }

    @ShellMethod(key = "show books", value = "Show all books.")
    public void displayBooksWithDetails() {
        bookDao.findAllWithAddInfo().forEach(it -> System.out.println(it.toString()));
    }

    @ShellMethod(key = "show genres", value = "Show all genres")
    public void displayGenres() {
        genreDao.findAll().forEach(g -> System.out.println(g.toString()));
    }

    @ShellMethod(key = "genre by id",value = "Find genre by id")
    public void findGenreById( long id) {
        System.out.println(genreDao.findById(id));
    }

    @ShellMethod(key = "genre by name", value = "Find genre by name")
    public void findGenreByName( String genre) {
        System.out.println(genreDao.findByGenre(genre));
    }

    @ShellMethod(key = "delete genre", value = "Delete genre by name")
    public void deleteGenreByName( String name) {
         Genre genre = genreDao.findByGenre(name);
        if (genre != null) {
            genreDao.delete(genre);
        } else {
            System.out.println(name + " not found");
        }
    }

    @ShellMethod(key = "save genre", value = "Save genre")
    public void saveGenre( Genre genre) {
        genreDao.save(genre);
    }


    @ShellMethod(key = "run h2console", value = "Start H2 console")
    public void dbConsole() throws SQLException {
        Console.main();
    }
}
