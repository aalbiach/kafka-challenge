package com.kafka.producer.shared.infrastructure.bus.kafka;

import com.kafka.producer.shared.infrastructure.bus.converter.DomainEventConverter;
import com.kafka.producer.shared.infrastructure.bus.kafka.publisher.DefaultKafkaPublisher;
import com.kafka.producer.shared.infrastructure.bus.publisher.AvroKafkaPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaOperations;

import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class KafkaPublisherConfig {

    private final KafkaOperations<String, Object> template;

    @SuppressWarnings("rawtypes")
    private final Set<DomainEventConverter> domainEventConverters;

    @Bean
    @ConditionalOnProperty(prefix = "spring.kafka.producer", name = "value-serializer", havingValue = "io.confluent.kafka.serializers.KafkaAvroSerializer")
    public KafkaPublisher avroPublisher() {
        return new AvroKafkaPublisher(template, domainEventConverters);
    }

    @Bean
    @ConditionalOnMissingBean
    public KafkaPublisher defaultPublisher() {
        return new DefaultKafkaPublisher(template);
    }

}
