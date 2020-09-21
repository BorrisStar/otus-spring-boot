package spring.service;

import org.springframework.stereotype.Service;
import spring.domain.Butterfly;
import spring.domain.Status;

@Service
public class ButterflyService {
    public Butterfly moveToButterfly(Butterfly butterfly) throws InterruptedException {
        System.out.println("Куколка превращается в бабочку");
        Thread.sleep(3000);
        butterfly.setStatus(Status.Butterfly.name());
        System.out.println("Status: " +butterfly.getStatus() + "Next -> Бабочка улетает");
        return butterfly;
    }
}
