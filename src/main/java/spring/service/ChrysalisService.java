package spring.service;

import org.springframework.stereotype.Service;
import spring.domain.Butterfly;
import spring.domain.Status;

@Service
public class ChrysalisService {
    public Butterfly moveToChrysalis( Butterfly butterfly) throws InterruptedException {
        System.out.println("Гусеница ест листья");
        Thread.sleep(3000);
        butterfly.setStatus(Status.Chrysalis.name());
        System.out.println("Status: " + butterfly.getStatus() + "Next -> Гусеница превращается в куколку");
        return butterfly;
    }
}
