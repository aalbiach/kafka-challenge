package com.kafka.producer.user.domain;

import com.kafka.producer.user.infrastructure.api.request.UserCreateRequest;
import com.kafka.producer.user.infrastructure.api.request.UserUpdateRequest;

public class UserMother {

    public static User create(UserId id, UserName name, UserEmail email) {
        return new User(id, name, email);
    }

    public static User random() {
        return create(UserIdMother.random(), UserNameMother.random(), UserEmailMother.random());
    }

    public static User fromRequest(UserCreateRequest request) {
        return create(UserIdMother.random(), UserNameMother.create(request.getName()), UserEmailMother.create(request.getEmail()));
    }

    public static User fromRequest(UserUpdateRequest request) {
        return create(UserIdMother.random(), UserNameMother.create(request.getName()), UserEmailMother.create(request.getEmail()));
    }

    public static User withName(String name) {
        return create(UserIdMother.random(), UserNameMother.create(name), UserEmailMother.random());
    }

}
