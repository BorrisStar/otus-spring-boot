package spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;


@SpringBootApplication
public class OtusApplication {
    public static void main(String[] args) throws SQLException {
        SpringApplication.run(OtusApplication.class, args);
    }
}
