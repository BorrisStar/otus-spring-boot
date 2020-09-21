package spring.service;

import org.springframework.stereotype.Service;
import spring.domain.Butterfly;
import spring.domain.Entomologist;
import spring.domain.Status;

@Service
public class CaterpillarService {
    public Butterfly moveToCaterpillar(Entomologist entomologist) throws InterruptedException {
        System.out.println("entomologist " +entomologist.getName() + "  помещает яйцо в инкубатор");
        Thread.sleep(3000);
        Butterfly butterfly = new Butterfly(Status.Caterpillar.name());
        System.out.println("Status: " +butterfly.getStatus() + "Next ->  Яйцо вылупляется и превращается в гусеницу");
        return butterfly;
    }
}
