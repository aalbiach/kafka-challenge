package com.kafka.producer.user.domain.event;

import com.kafka.producer.user.domain.*;

public final class UserDeletedDomainEventMother {

    public static UserDeletedDomainEvent create(UserId id, UserName name, UserEmail email, UserActive active) {
        return new UserDeletedDomainEvent(id.value(), name.value(), email.value(), active.value());
    }

    public static UserDeletedDomainEvent fromUser(User user) {
        return create(user.id(), user.name(), user.email(), user.active());
    }

    public static UserDeletedDomainEvent random() {
        return create(UserIdMother.random(), UserNameMother.random(), UserEmailMother.random(), UserActiveMother.random());
    }

}
