package com.kafka.producer.user.domain.exception;

import com.kafka.producer.shared.domain.exception.BadRequest;
import com.kafka.producer.user.domain.UserEmail;
import org.springframework.lang.NonNull;

public class UserExists extends BadRequest {

    public UserExists(@NonNull UserEmail email) {
        super("user_exists", "The user <%s> already exists".formatted(email.value()));
    }

}
