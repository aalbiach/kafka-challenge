package com.kafka.consumer.user.infrastructure.bus.converter.avro;

import com.kafka.consumer.shared.infrastructure.DomainEventConverter;
import com.kafka.consumer.shared.infrastructure.bus.converter.avro.AbstractEventConverter;
import com.kafka.consumer.user.domain.event.UserDeletedEvent;
import org.apache.avro.generic.GenericRecord;
import org.springframework.stereotype.Component;

@Component
public final class UserDeletedEventConverter extends AbstractEventConverter
        implements DomainEventConverter<GenericRecord, UserDeletedEvent> {

    @Override
    public boolean canApply(GenericRecord event) {
        return event.getSchema().getName().equals("UserDeletedAvroEvent");
    }

    @Override
    public UserDeletedEvent convert(GenericRecord event) {
        var aggregateId = getString(getFieldValue(event, "aggregateId"));
        var eventId     = getString(getFieldValue(event, "eventId"));
        var occurredOn  = getOffsetDateTime(getFieldValue(event, "occurredOn"));
        var name        = getString(getFieldValue(event, "name"));
        var email       = getString(getFieldValue(event, "email"));
        var active      = getBoolean(getFieldValue(event, "active"));

        return new UserDeletedEvent(aggregateId, eventId, occurredOn, name, email, active);
    }

}
