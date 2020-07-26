package spring.changelog;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import spring.model.Author;
import spring.model.Book;
import spring.model.Comment;
import spring.model.DBSequence;
import spring.model.Genre;

import java.util.ArrayList;
import java.util.List;

@ChangeLog
public class DBChangeLog {

    @Autowired
    MongoTemplate mongoTemplate;

    @ChangeSet(order = "001", id = "addeBooks", author = "borris")
    public void insertBooks(MongoTemplate mongoTemplate) {
        Author author1 = new Author("Andrey", "Lukyanenko");
        Author author2 = new Author("Aleksey", "Isaev");
        Author author3 = new Author("University", "of Oxford");

        Genre genre1 = new Genre("Military");
        Genre genre2 = new Genre("Fantasy");
        Genre genre3 = new Genre("Dictionary");

        Comment comment1 = new Comment("Unbelievable!");
        Comment comment2 = new Comment("Fantastic!!");
        Comment comment3 = new Comment("So-so!");
        Comment comment4 = new Comment("Usable.");
        Comment comment5 = new Comment("Good!");
        Comment comment6 = new Comment("Not bad...");
        Comment comment7 = new Comment("So boring..");
        Comment comment8 = new Comment("No comments");

        List<Comment> comments = new ArrayList<>();

        comments.add(comment1);
        comments.add(comment2);
        Book book1 = new Book("the night Watch", 1, 2, "1995", comments);
        comments.clear();

        comments.add(comment3);
        comments.add(comment4);
        Book book2 = new Book("Battle in the den of the Beast.", 2, 1, "2007", comments);
        comments.clear();

        comments.add(comment5);
        comments.add(comment6);
        Book book3 = new Book("The miracle near Moscow", 2, 1, "2017", comments);
        comments.clear();

        comments.add(comment7);
        comments.add(comment8);
        Book book4 = new Book("Oxford Dictionary", 3, 3, "2010", comments);
        comments.clear();


        mongoTemplate.save(genre1);
        mongoTemplate.save(genre2);
        mongoTemplate.save(author1);
        mongoTemplate.save(author2);
        mongoTemplate.save(author3);
        mongoTemplate.save(comment1);
        mongoTemplate.save(comment2);
        mongoTemplate.save(comment3);
        mongoTemplate.save(comment4);
        mongoTemplate.save(comment5);
        mongoTemplate.save(comment6);
        mongoTemplate.save(comment7);
        mongoTemplate.save(comment8);
        mongoTemplate.save(book1);
        mongoTemplate.save(book2);
        mongoTemplate.save(book3);
        mongoTemplate.save(book4);

        DBSequence authorSequence = new DBSequence("author_sequence", 3);
        DBSequence genreSequence = new DBSequence("genre_sequence", 3);
        DBSequence bookSequence = new DBSequence("book_sequence", 4);
        DBSequence commentSequence = new DBSequence("comment_sequence", 8);

        mongoTemplate.save(authorSequence);
        mongoTemplate.save(genreSequence);
        mongoTemplate.save(bookSequence);
        mongoTemplate.save(commentSequence);
    }
}
