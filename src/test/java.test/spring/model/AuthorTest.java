package spring.model;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
@EnableConfigurationProperties
public class AuthorTest {

    @Test
    void constructor_correctObject() {
        Author author = new Author("firstName", "lastName");

        assertEquals("firstName", author.getFirstname());
        assertEquals("lastName", author.getLastname());
    }
}
