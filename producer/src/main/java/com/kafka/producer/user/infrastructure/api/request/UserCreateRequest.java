package com.kafka.producer.user.infrastructure.api.request;

import lombok.Value;

@Value
public class UserCreateRequest {

    String name;
    String email;

}
