package com.kafka.producer.shared.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode
public abstract class Identifier implements Serializable {

    @Accessors(fluent = true)
    private final String value;

    public Identifier(String value) {
        ensureValidUuid(value);

        this.value = value;
    }

    private void ensureValidUuid(String value) throws IllegalArgumentException {
        //noinspection ResultOfMethodCallIgnored
        UUID.fromString(value);
    }

}
