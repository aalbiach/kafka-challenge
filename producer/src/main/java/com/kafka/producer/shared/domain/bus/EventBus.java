package com.kafka.producer.shared.domain.bus;

import com.kafka.producer.shared.domain.bus.event.DomainEvent;

import java.util.List;

public interface EventBus {

    void publish(final List<DomainEvent> events);

    void publish(final DomainEvent event);

}
