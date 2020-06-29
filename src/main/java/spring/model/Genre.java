package spring.model;

public class Genre {
    private long id;
    private String genre;

    public Genre(long id, String genre) {
        this.id = id;
        this.genre = genre;
    }

    public Genre() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Genre{" +
               "id=" + id +
               ", genre='" + genre + '\'' +
               '}';
    }
}
