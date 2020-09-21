package spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import spring.config.ButterflyByEntomologistGateway;
import spring.domain.Butterfly;
import spring.domain.Entomologist;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommand {

    private static final AtomicInteger processNumber = new AtomicInteger(1);
    private final ButterflyByEntomologistGateway butterflyByEntomologistGateway;
    private static final String[] firstNames = {"Agent","James"};
    private static final String[] lastNames = {"007","Bong"};

    @ShellMethod("start")
    public void start() throws InterruptedException {
        while (true) {
            Thread.sleep(4000);
            Thread thread = new Thread(() -> {
                Entomologist entomologist = new Entomologist(firstNames[new Random().nextInt(2)] + " " + lastNames[new Random().nextInt(2)]);
                System.out.println("Положили новое яйцо в инкубатор: " + processNumber.getAndIncrement() + " entomologist: " + entomologist.getName());
                Butterfly butterfly = butterflyByEntomologistGateway.process(entomologist);
                System.out.println("Метаморфозы завершены: " + butterfly.toString());
            });
            thread.start();
        }
    }
//caterpillar chrysalis butterfly
}
