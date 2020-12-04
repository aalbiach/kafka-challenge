package com.kafka.producer.user.infrastructure.controller.request;

import lombok.Value;

@Value
public class UserCreateRequest {

    String name;
    String email;

}
