package spring.model;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthorTest {

    @Test
    void constructor_correctObject() {
        Author author = new Author("firstName", "lastName");

        assertEquals("firstName", author.getFirstname());
        assertEquals("lastName", author.getLastname());
    }
}
