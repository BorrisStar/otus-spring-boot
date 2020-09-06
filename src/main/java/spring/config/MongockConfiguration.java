package spring.config;

import com.github.cloudyrock.mongock.SpringBootMongock;
import com.github.cloudyrock.mongock.SpringBootMongockBuilder;
import com.mongodb.MongoClient;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.changelog.DBChangeLog;

@Configuration
@AllArgsConstructor
public class MongockConfiguration {
    private final MongoDBProperties mongoDBProperties;

    @Bean
    public SpringBootMongock mongock(ApplicationContext springContext, MongoClient mongoClient) {
        return new SpringBootMongockBuilder(mongoClient, mongoDBProperties.getDatabase(), DBChangeLog.class.getPackage().getName())
                .setApplicationContext(springContext)
                .setLockQuickConfig()
                .build();
    }

}
