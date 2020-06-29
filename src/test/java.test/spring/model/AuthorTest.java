package spring.model;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthorTest {

    @Test
    void constructor_correctObject() {
        Author author = new Author(100500L, "firstName", "lastName");

        assertEquals(100500L, author.getId());
        assertEquals("firstName", author.getFirstName());
        assertEquals("lastName", author.getLastName());
    }
}
