package com.kafka.producer.user.domain.exception;

import com.kafka.producer.shared.domain.exception.NotFound;
import com.kafka.producer.user.domain.UserEmail;
import com.kafka.producer.user.domain.UserId;
import org.springframework.lang.NonNull;

public class UserNotFound extends NotFound {

    public UserNotFound(@NonNull UserId id) {
        super("user_not_found", "The user <%s> not found".formatted(id.value()));
    }

    public UserNotFound(@NonNull UserEmail email) {
        super("user_not_found", "The user <%s> not found".formatted(email.value()));
    }

}
