package com.kafka.producer.user.domain;

import com.kafka.producer.shared.domain.BooleanMother;

public class UserActiveMother {

    public static UserActive create(Boolean value) {
        return new UserActive(value);
    }

    public static UserActive random() {
        return create(BooleanMother.random());
    }

}
