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
@Document(collection = "comment")
public class Comment {
    @Transient
    public static final String SEQUENCE_NAME ="comments_sequence";

    @Id
    @ToString.Include
    @EqualsAndHashCode.Include
    private long id;

    private String text;

    public Comment(String text) {
        this.text = text;
    }
    public Comment() {
    }

    @Override
    public String toString() {
        return "Comment{" +
               "id=" + id +
               ", text='" + text + '\'' +
               '}';
    }
}
