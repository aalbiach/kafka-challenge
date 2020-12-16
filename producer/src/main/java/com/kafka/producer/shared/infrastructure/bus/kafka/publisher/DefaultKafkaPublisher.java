package com.kafka.producer.shared.infrastructure.bus.kafka.publisher;

import com.kafka.producer.shared.domain.bus.event.DomainEvent;
import com.kafka.producer.shared.infrastructure.bus.kafka.KafkaPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaOperations;

@RequiredArgsConstructor
public final class DefaultKafkaPublisher implements KafkaPublisher {

    private final KafkaOperations<String, Object> template;

    @Override
    public void publish(String topicName, DomainEvent domainEvent) throws KafkaException {
        template.send(topicName, domainEvent);
    }

    @Override
    public void publish(String topicName, String key, DomainEvent domainEvent) throws KafkaException {
        template.send(topicName, key, domainEvent);
    }

}
