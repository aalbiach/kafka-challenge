package com.kafka.producer.shared.infrastructure.bus.publisher;

import com.kafka.producer.shared.domain.bus.event.DomainEvent;
import com.kafka.producer.shared.infrastructure.bus.converter.DomainEventConverter;
import com.kafka.producer.shared.infrastructure.bus.kafka.KafkaPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaOperations;

import java.util.Set;
import java.util.function.Consumer;

@RequiredArgsConstructor
@SuppressWarnings({"rawtypes", "unchecked"})
public final class AvroKafkaPublisher implements KafkaPublisher {

    private final KafkaOperations<String, Object> template;

    private final Set<DomainEventConverter> domainEventConverters;

    public void publish(String topicName, DomainEvent domainEvent) throws KafkaException {
        convertAndSend(domainEvent, domainEventConverter -> template.send(topicName, domainEventConverter.convert(domainEvent)));
    }

    public void publish(String topicName, String key, DomainEvent domainEvent) throws KafkaException {
        convertAndSend(domainEvent, domainEventConverter -> template.send(topicName, key, domainEventConverter.convert(domainEvent)));
    }

    void convertAndSend(DomainEvent domainEvent, Consumer<DomainEventConverter> action) {
        domainEventConverters.stream()
                .filter(domainEventConverter -> domainEventConverter.canApply(domainEvent))
                .forEach(action);
    }
}
