package spring.shell;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommand {

    private final JobLauncher jobLauncher;

    private final Job fromMongoToPostgres;

    @SneakyThrows
    @ShellMethod(value = "StartMigrationFromMongoToPostgres", key = "s")
    public void startMigrationFromMongoToPostgres() {
        jobLauncher.run(fromMongoToPostgres, new JobParameters());
    }

}
