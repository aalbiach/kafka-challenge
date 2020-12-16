package com.kafka.producer.user.domain.event;

import com.kafka.producer.user.domain.*;

public final class UserUpdatedDomainEventMother {

    public static UserUpdatedDomainEvent create(UserId id, UserName name, UserEmail email, UserActive active) {
        return new UserUpdatedDomainEvent(id.value(), name.value(), email.value(), active.value());
    }

    public static UserUpdatedDomainEvent fromUser(User user) {
        return create(user.id(), user.name(), user.email(), user.active());
    }

    public static UserUpdatedDomainEvent random() {
        return create(UserIdMother.random(), UserNameMother.random(), UserEmailMother.random(), UserActiveMother.random());
    }

}
