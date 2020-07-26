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
@Document(collection = "author")
public class Author {

    @Transient
    public static final String SEQUENCE_NAME = "authors_sequence";

    @Id
    @ToString.Include
    @EqualsAndHashCode.Include
    private long id;

    private String firstname;
    private String lastname;

    public Author( String firstName, String lastName) {
        this.firstname = firstName;
        this.lastname = lastName;
    }

    public Author() {
    }

    @Override
    public String toString() {
        return "Author{" +
               "id=" + id +
               ", firstname='" + firstname + '\'' +
               ", lastname='" + lastname + '\'' +
               '}';
    }
}
