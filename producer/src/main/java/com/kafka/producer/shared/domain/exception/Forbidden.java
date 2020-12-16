package com.kafka.producer.shared.domain.exception;

import org.springframework.lang.NonNull;

public abstract class Forbidden extends DomainException {

    public Forbidden(@NonNull String errorCode, @NonNull String errorMessage) {
        super(errorCode, errorMessage);
    }

}
