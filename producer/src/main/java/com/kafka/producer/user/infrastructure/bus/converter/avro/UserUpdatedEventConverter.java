package com.kafka.producer.user.infrastructure.bus.converter.avro;

import com.kafka.producer.shared.domain.bus.event.DomainEvent;
import com.kafka.producer.shared.infrastructure.bus.converter.DomainEventConverter;
import com.kafka.producer.shared.infrastructure.bus.converter.avro.AbstractEventConverter;
import com.kafka.producer.user.domain.event.UserUpdatedDomainEvent;
import com.kafka.producer.user.infrastructure.bus.kafka.event.UserUpdatedAvroEvent;
import org.springframework.stereotype.Component;

@Component
public final class UserUpdatedEventConverter extends AbstractEventConverter
        implements DomainEventConverter<UserUpdatedDomainEvent, UserUpdatedAvroEvent> {

    @Override
    public boolean canApply(DomainEvent event) {
        return event instanceof UserUpdatedDomainEvent;
    }

    @Override
    public UserUpdatedAvroEvent convert(UserUpdatedDomainEvent event) {
        return UserUpdatedAvroEvent.newBuilder()
                .setAggregateId(toUtf8(event.getAggregateId()))
                .setEventId(toUtf8(event.getEventId()))
                .setOccurredOn(toMillis(event.getOccurredOn()))
                .setName(toUtf8(event.getName()))
                .setEmail(toUtf8(event.getEmail()))
                .setActive(event.getActive())
                .build();
    }

}
