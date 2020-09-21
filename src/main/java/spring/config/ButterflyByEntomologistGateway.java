package spring.config;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import spring.domain.Butterfly;
import spring.domain.Entomologist;

@MessagingGateway
public interface ButterflyByEntomologistGateway {
    @Gateway(requestChannel = "entomologistChannel", replyChannel = "metamorphosisChannel")
    Butterfly process(Entomologist entomologist);
}
