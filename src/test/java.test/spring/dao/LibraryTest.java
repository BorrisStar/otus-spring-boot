package spring.dao;

import org.junit.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBatchTest
@SpringBootTest
public class LibraryTest {

    private final JobLauncherTestUtils jobLauncherTestUtils = new JobLauncherTestUtils();

    @Test
    public void testMongoToPostgresJob() throws Exception {

        JobExecution jobExecution = jobLauncherTestUtils
                .launchJob();
        assertThat(jobExecution.getExitStatus()
                .getExitCode())
                .isEqualTo("COMPLETED");

        Job job = jobLauncherTestUtils.getJob();
        assertThat(job).isNotNull()
                .extracting(Job::getName)
                .isEqualTo("migrateMongoToTPostgres");


    }
}
