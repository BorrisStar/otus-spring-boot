package spring.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collection = "genre")
public class Genre {
    @Transient
    public static final String SEQUENCE_NAME ="genres_sequence";

    @Id
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
