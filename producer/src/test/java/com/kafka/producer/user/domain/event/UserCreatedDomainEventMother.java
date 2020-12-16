package com.kafka.producer.user.domain.event;

import com.kafka.producer.user.domain.*;

public final class UserCreatedDomainEventMother {

    public static UserCreatedDomainEvent create(UserId id, UserName name, UserEmail email) {
        return new UserCreatedDomainEvent(id.value(), name.value(), email.value(), false);
    }

    public static UserCreatedDomainEvent fromUser(User user) {
        return create(user.id(), user.name(), user.email());
    }

    public static UserCreatedDomainEvent random() {
        return create(UserIdMother.random(), UserNameMother.random(), UserEmailMother.random());
    }

}
