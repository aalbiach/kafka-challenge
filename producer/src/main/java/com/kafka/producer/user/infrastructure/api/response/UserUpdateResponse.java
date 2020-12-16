package com.kafka.producer.user.infrastructure.api.response;

import com.kafka.producer.user.domain.User;
import lombok.Value;

@Value
public class UserUpdateResponse {

    String id;
    String name;
    String email;

    public static UserUpdateResponse fromUser(User user) {
        return new UserUpdateResponse(
                user.id().value(),
                user.name().value(),
                user.email().value()
        );
    }

}
