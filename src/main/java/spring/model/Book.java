package spring.model;

public class Book {
    private long id;
    private String title;
    private long author;
    private long genre;
    private String year;

    public Book(long id, String title, long authorId, long genreId, String year) {
        this.id = id;
        this.title = title;
        this.author = authorId;
        this.genre = genreId;
        this.year = year;
    }

    public Book() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getAuthor() {
        return author;
    }

    public void setAuthor(long author) {
        this.author = author;
    }

    public long getGenre() {
        return genre;
    }

    public void setGenre(long genre) {
        this.genre = genre;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Book{" +
               "id=" + id +
               ", title='" + title + '\'' +
               ", author=" + author +
               ", genre=" + genre +
               ", year='" + year + '\'' +
               '}';
    }
}
