package com.kafka.producer.shared.infrastructure.bus.kafka;

import com.kafka.producer.shared.domain.bus.event.DomainEvent;
import org.springframework.kafka.KafkaException;

public interface KafkaPublisher {

    void publish(String topicName, DomainEvent domainEvent) throws KafkaException;

    void publish(String topicName, String key, DomainEvent domainEvent) throws KafkaException;

}
