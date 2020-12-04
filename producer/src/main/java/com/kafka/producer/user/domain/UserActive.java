package com.kafka.producer.user.domain;

import com.kafka.producer.shared.domain.vo.BooleanValueObject;

public class UserActive extends BooleanValueObject {

    public UserActive(Boolean value) {
        super(value);
    }

    public static UserActive active() {
        return new UserActive(true);
    }

    public static UserActive inactive() {
        return new UserActive(false);
    }

}
