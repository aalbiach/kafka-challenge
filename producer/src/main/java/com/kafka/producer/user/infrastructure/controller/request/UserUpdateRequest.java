package com.kafka.producer.user.infrastructure.controller.request;

import lombok.Value;

@Value
public class UserUpdateRequest {

    String name;
    String email;

}
