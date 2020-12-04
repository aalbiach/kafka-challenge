package com.kafka.producer.shared.domain;

import com.kafka.producer.shared.domain.bus.event.DomainEvent;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class AggregateRoot {

    private final List<DomainEvent> domainEvents = new LinkedList<>();

    final public List<DomainEvent> pullDomainEvents() {
        List<DomainEvent> events = new ArrayList<>(domainEvents);

        domainEvents.clear();

        return events;
    }

    final protected void record(DomainEvent event) {
        domainEvents.add(event);
    }

}
