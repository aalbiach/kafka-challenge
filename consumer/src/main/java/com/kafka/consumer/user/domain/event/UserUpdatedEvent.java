package com.kafka.consumer.user.domain.event;

import com.kafka.consumer.shared.domain.bus.event.DomainEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserUpdatedEvent extends DomainEvent {

    String name;

    String email;

    Boolean active;

    public UserUpdatedEvent(
            String aggregateId,
            String eventId,
            OffsetDateTime occurredOn,
            String name,
            String email,
            Boolean active) {
        super(aggregateId, eventId, occurredOn);
        this.name = name;
        this.email = email;
        this.active = active;
    }

}
