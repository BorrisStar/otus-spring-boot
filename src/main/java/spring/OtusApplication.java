package spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import spring.service.ScannerServiceImpl;
import spring.service.StudentSurveyServiceImpl;

import java.io.IOException;

@SpringBootApplication
public class OtusApplication {
    public static void main(String[] args) throws IOException {
        var context = SpringApplication.run(OtusApplication.class, args);

        StudentSurveyServiceImpl surveyService = context.getBean(StudentSurveyServiceImpl.class);
        surveyService.questioning(new ScannerServiceImpl().getScannerIn());
    }
}
