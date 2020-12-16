package com.kafka.producer.user.infrastructure.api.request;

import lombok.Value;

@Value
public class UserUpdateRequest {

    String name;
    String email;

}
