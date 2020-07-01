package spring.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Include
    @EqualsAndHashCode.Include
    private long id;

    private String title;
    private long author;
    private long genre;
    private String year;

    @Setter
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Comment> commentList = new ArrayList<>();

    public Book(String title, long author, long genre, String year) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.year = year;
    }

    public Book(long id, String title, long author, long genre, String year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.year = year;
    }

    public Book() {
    }
}
