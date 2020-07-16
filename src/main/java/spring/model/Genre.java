package spring.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Include
    @EqualsAndHashCode.Include
    private long id;

    private String genre;

    public Genre(String genre) {
        this.genre = genre;
    }

    public Genre(long id, String genre) {
        this.id = id;
        this.genre = genre;
    }

    public Genre() {

    }

    @Override
    public String toString() {
        return "Genre{" +
               "id=" + id +
               ", genre='" + genre + '\'' +
               '}';
    }
}
