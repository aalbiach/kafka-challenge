package com.kafka.consumer.shared.domain.bus.event;

import lombok.*;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class DomainEvent {

    private String aggregateId;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private String eventId;

    @EqualsAndHashCode.Exclude
    private OffsetDateTime occurredOn;

}
