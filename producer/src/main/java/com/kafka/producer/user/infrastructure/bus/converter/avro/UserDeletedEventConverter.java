package com.kafka.producer.user.infrastructure.bus.converter.avro;

import com.kafka.producer.shared.domain.bus.event.DomainEvent;
import com.kafka.producer.shared.infrastructure.bus.converter.DomainEventConverter;
import com.kafka.producer.shared.infrastructure.bus.converter.avro.AbstractEventConverter;
import com.kafka.producer.user.domain.event.UserDeletedDomainEvent;
import com.kafka.producer.user.infrastructure.bus.kafka.event.UserDeletedAvroEvent;
import org.springframework.stereotype.Component;

@Component
public final class UserDeletedEventConverter extends AbstractEventConverter
        implements DomainEventConverter<UserDeletedDomainEvent, UserDeletedAvroEvent> {

    @Override
    public boolean canApply(DomainEvent event) {
        return event instanceof UserDeletedDomainEvent;
    }

    @Override
    public UserDeletedAvroEvent convert(UserDeletedDomainEvent event) {
        return UserDeletedAvroEvent.newBuilder()
                .setAggregateId(toUtf8(event.getAggregateId()))
                .setEventId(toUtf8(event.getEventId()))
                .setOccurredOn(toMillis(event.getOccurredOn()))
                .setName(toUtf8(event.getName()))
                .setEmail(toUtf8(event.getEmail()))
                .setActive(event.getActive())
                .build();
    }

}
