package com.kafka.producer.shared.infrastructure.bus.converter.avro;

import org.apache.avro.util.Utf8;

import java.time.OffsetDateTime;

public abstract class AbstractEventConverter {

    protected Utf8 toUtf8(String data) {
        return new Utf8(data);
    }

    protected long toMillis(OffsetDateTime data) {
        return data.toInstant().toEpochMilli();
    }

}
