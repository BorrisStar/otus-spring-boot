package spring.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collection = "book")
public class Book {
    @Transient
    public static final String SEQUENCE_NAME = "books_sequence";

    @Id
    @ToString.Include
    @EqualsAndHashCode.Include
    private long id;

    private String title;
    private long author;
    private long genre;
    private String year;

    @DBRef
    private List<Comment> commentList = new ArrayList<>();

    public Book(String title, long author, long genre, String year, List<Comment> commentList) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.year = year;
        this.commentList = commentList;
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
