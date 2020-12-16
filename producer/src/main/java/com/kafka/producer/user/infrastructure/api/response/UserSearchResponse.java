package com.kafka.producer.user.infrastructure.api.response;

import com.kafka.producer.user.domain.User;
import lombok.Value;

@Value
public class UserSearchResponse {

    String id;
    String name;
    String email;

    public static UserSearchResponse fromUser(User user) {
        return new UserSearchResponse(
                user.id().value(),
                user.name().value(),
                user.email().value()
        );
    }

}
