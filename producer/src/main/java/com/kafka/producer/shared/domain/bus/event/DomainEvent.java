package com.kafka.producer.shared.domain.bus.event;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public abstract class DomainEvent {

    private final String aggregateId;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private final String eventId;

    @EqualsAndHashCode.Exclude
    private final OffsetDateTime occurredOn;

    public DomainEvent(String aggregateId) {
        this.aggregateId = aggregateId;
        this.eventId     = UUID.randomUUID().toString();
        this.occurredOn  = OffsetDateTime.now();
    }

    public abstract String eventName();

}
