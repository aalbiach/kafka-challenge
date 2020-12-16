package com.kafka.producer.shared.domain.exception;

import org.springframework.lang.NonNull;

public abstract class NotFound extends DomainException {

    public NotFound(@NonNull String errorCode, @NonNull String errorMessage) {
        super(errorCode, errorMessage);
    }

}
