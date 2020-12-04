package com.kafka.producer.user.infrastructure.controller.response;

import com.kafka.producer.user.domain.User;
import lombok.Value;

@Value
public class UserCreateResponse {

    String id;
    String name;
    String email;

    public static UserCreateResponse fromUser(User user) {
        return new UserCreateResponse(
                user.id().value(),
                user.name().value(),
                user.email().value()
        );
    }

}
