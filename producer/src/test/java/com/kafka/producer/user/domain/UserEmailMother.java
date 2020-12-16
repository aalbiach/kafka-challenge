package com.kafka.producer.user.domain;

import com.kafka.producer.shared.domain.EmailMother;

public class UserEmailMother {

    public static UserEmail create(String value) {
        return new UserEmail(value);
    }

    public static UserEmail random() {
        return create(EmailMother.random());
    }

}
