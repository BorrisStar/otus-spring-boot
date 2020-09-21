package spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;

import java.sql.SQLException;


@EnableIntegration
@IntegrationComponentScan
@SpringBootApplication
public class OtusApplication {
    public static void main(String[] args) throws SQLException {
        SpringApplication.run(OtusApplication.class, args);
    }
}
