package com.kafka.producer.shared.domain.exception;

import org.springframework.lang.NonNull;

public abstract class BadRequest extends DomainException {

    public BadRequest(@NonNull String errorCode, @NonNull String errorMessage) {
        super(errorCode, errorMessage);
    }

}
