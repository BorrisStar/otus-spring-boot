package spring.model;

public class Book {
    private long id;
    private String title;
    private long author;
    private long genre;
    private String year;

    public Book(String title, long author, long genre, String year) {
        this.title = title;
        this.author = author;
        this.genre = genre;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        Book book = (Book) o;

        if (id != book.id) { return false; }
        if (author != book.author) { return false; }
        if (genre != book.genre) { return false; }
        if (title != null ? !title.equals(book.title) : book.title != null) { return false; }
        return year != null ? year.equals(book.year) : book.year == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (int) (author ^ (author >>> 32));
        result = 31 * result + (int) (genre ^ (genre >>> 32));
        result = 31 * result + (year != null ? year.hashCode() : 0);
        return result;
    }
}
