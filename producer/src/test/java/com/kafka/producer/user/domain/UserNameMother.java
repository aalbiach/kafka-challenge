package com.kafka.producer.user.domain;

import com.kafka.producer.shared.domain.WordMother;

public class UserNameMother {

    public static UserName create(String value) {
        return new UserName(value);
    }

    public static UserName random() {
        return create(WordMother.random());
    }

}
