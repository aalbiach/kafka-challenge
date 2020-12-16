package com.kafka.producer.user.domain.event;

import com.kafka.producer.shared.domain.bus.event.DomainEvent;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.OffsetDateTime;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class UserCreatedDomainEvent extends DomainEvent {

    private final String  name;
    private final String  email;
    private final Boolean active;

    public UserCreatedDomainEvent() {
        super(null);

        this.name   = null;
        this.email  = null;
        this.active = null;
    }

    public UserCreatedDomainEvent(String aggregateId, String name, String email, Boolean active) {
        super(aggregateId);

        this.name   = name;
        this.email  = email;
        this.active = active;
    }

    public UserCreatedDomainEvent(
            String aggregateId,
            String eventId,
            OffsetDateTime occurredOn,
            String name,
            String email,
            Boolean active
    ) {
        super(aggregateId, eventId, occurredOn);

        this.name   = name;
        this.email  = email;
        this.active = active;
    }

    @Override
    public String eventName() {
        return "user.created";
    }

}
