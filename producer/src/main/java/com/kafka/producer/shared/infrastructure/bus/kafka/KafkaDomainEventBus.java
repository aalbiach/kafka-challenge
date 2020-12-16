package com.kafka.producer.shared.infrastructure.bus.kafka;

import com.kafka.producer.shared.domain.bus.EventBus;
import com.kafka.producer.shared.domain.bus.event.DomainEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.KafkaException;
import org.springframework.stereotype.Component;

import java.util.List;

@Log4j2
@Component
@RequiredArgsConstructor
public class KafkaDomainEventBus implements EventBus {

    private static final String TOPIC_QUEUE_DOMAIN_EVENTS = "queue.domain-events";

    private final KafkaPublisher publisher;

    @Override
    public void publish(List<DomainEvent> events) {
        events.forEach(this::publish);
    }

    @Override
    public void publish(DomainEvent domainEvent) {
        var topicName = composeTopicName(domainEvent);

        try {
            log.info("Publishing domain event. [topic='{}', event={}]", topicName, domainEvent);
            publisher.publish(topicName, domainEvent.getAggregateId(), domainEvent);
        } catch (KafkaException exception) {
            log.error("Error publishing domain event: [topic='{}', event={}]", topicName, domainEvent);
        }
    }

    private String composeTopicName(DomainEvent domainEvent) {
        return TOPIC_QUEUE_DOMAIN_EVENTS + "." + domainEvent.eventName();
    }

}
