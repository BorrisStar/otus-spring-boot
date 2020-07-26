package spring.config;

import com.github.mongobee.Mongobee;
import com.mongodb.MongoClient;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import spring.changelog.DBChangeLog;

@Configuration
@AllArgsConstructor
public class MongoBeeConfiguration {
    private final MongoClient mongo;
    private final MongoTemplate mongoTemplate;
    private final MongoDBProperties mongoDBProperties;

    @Bean
    public Mongobee mongobee(Environment environment) {
        Mongobee runner = new Mongobee(mongo);
        runner.setDbName(mongoDBProperties.getDatabase());
        runner.setChangeLogsScanPackage(DBChangeLog.class.getPackage().getName());
        runner.setSpringEnvironment(environment);
        runner.setMongoTemplate(mongoTemplate);
        return runner;
    }

}
