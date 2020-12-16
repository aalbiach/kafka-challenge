package com.kafka.producer.shared.domain.exception;

import org.springframework.lang.NonNull;

public class DomainException extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;

    public DomainException(@NonNull String errorCode, @NonNull String errorMessage) {
        super(errorMessage);

        this.errorCode    = errorCode;
        this.errorMessage = errorMessage;
    }

    public String errorCode() {
        return errorCode;
    }

    public String errorMessage() {
        return errorMessage;
    }

}
