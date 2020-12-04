package com.kafka.producer.shared.infrastructure.bus.kafka;

import com.kafka.producer.shared.infrastructure.bus.kafka.publisher.DefaultKafkaPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaOperations;

@Configuration
@RequiredArgsConstructor
public class KafkaPublisherConfig {

    private final KafkaOperations<String, Object> template;

    @Bean
    @ConditionalOnMissingBean
    public KafkaPublisher defaultPublisher() {
        return new DefaultKafkaPublisher(template);
    }

}
