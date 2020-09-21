package spring.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import spring.service.ButterflyService;
import spring.service.CaterpillarService;
import spring.service.ChrysalisService;

@Configuration
@RequiredArgsConstructor
public class ButterflyFlowConfiguration {
    private final ButterflyService butterflyService;
    private final CaterpillarService caterpillarService;
    private final ChrysalisService chrysalisService;

    @Bean
    public QueueChannel entomologistChannel() {
        return MessageChannels.queue(10).get();
    }

    @Bean
    public PublishSubscribeChannel metamorphosisChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER )
    public PollerMetadata poller () {
        return Pollers.fixedRate(100).maxMessagesPerPoll(2).get() ;
    }

    @Bean
    public IntegrationFlow workFlow() {
        return IntegrationFlows.from("entomologistChannel")
                .handle(caterpillarService, "moveToCaterpillar")
                .handle(chrysalisService,"moveToChrysalis")
                .handle(butterflyService,"moveToButterfly")
                .channel("metamorphosisChannel")
                .get();
    }
}
