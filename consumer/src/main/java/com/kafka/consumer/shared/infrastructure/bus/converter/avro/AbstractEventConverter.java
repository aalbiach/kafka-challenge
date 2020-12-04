package com.kafka.consumer.shared.infrastructure.bus.converter.avro;

import org.apache.avro.generic.GenericRecord;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Optional;

public abstract class AbstractEventConverter {

    protected Object getFieldValue(GenericRecord event, String field) {
        return event.hasField(field)
                ? event.get(field)
                : event.getSchema().getField(field).defaultVal();
    }

    protected String getString(Object value) {
        return Optional.ofNullable(value)
                .map(Object::toString)
                .orElse(null);
    }

    protected Instant getInstant(long milliseconds) {
        return Optional.ofNullable(milliseconds)
                .map(Instant::ofEpochMilli)
                .orElse(null);
    }

    protected OffsetDateTime getOffsetDateTime(Object milliseconds) {
        return Optional.ofNullable(milliseconds)
                .map(o -> getInstant((long) o).atZone(ZoneId.systemDefault()).toOffsetDateTime())
                .orElse(null);
    }

    protected Boolean getBoolean(Object value) {
        return (Boolean) Optional.ofNullable(value).orElse(null);
    }

}
