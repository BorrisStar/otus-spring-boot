package spring.model;

public class SqlQuery {
    public static String SQL_QUERY_FIND_AUTHOR_BY_NAME = "SELECT * FROM author WHERE LOWER(CONCAT(firstname, ' ', lastname)) LIKE LOWER(:name)";
    public static String SQL_QUERY_FIND_BY_TITLE = "SELECT * FROM book WHERE LOWER(title) LIKE LOWER(:title)";
    public static String SQL_QUERY_FIND_BY_AUTHOR = "SELECT book.id, book.title, book.author, book.genre, book.written FROM book INNER JOIN author ON book.author = author.id WHERE LOWER(CONCAT(firstname, ' ', lastname)) LIKE LOWER(:name)";
    public static String SQL_QUERY_FIND_ALL_WITH_ADD_INFO = "SELECT book.id, book.title, CONCAT(author.firstname, ' ', author.lastname) AS author, genre.genre, book.year FROM book INNER JOIN author INNER JOIN genre ON book.author = author.id AND book.genre = genre.id";
    public static String SQL_QUERY_FIND_GENRE_BY_NAME = "SELECT * FROM genre WHERE LOWER(genre) LIKE LOWER(:genre)";
    public static String SQL_QUERY_FIND_BY_GENRE = "SELECT book.id, book.title, book.author, book.genre, book.written FROM book INNER JOIN genre ON book.genre = genre.id WHERE LOWER(genre) LIKE LOWER(:genre)";

}
