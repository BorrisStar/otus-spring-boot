package spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.sql.SQLException;


@SpringBootApplication
@EnableConfigurationProperties
@EnableMongoRepositories() //to activate MongoDB repositories
public class OtusApplication {
    public static void main(String[] args) throws SQLException {
        SpringApplication.run(OtusApplication.class, args);
    }
}
