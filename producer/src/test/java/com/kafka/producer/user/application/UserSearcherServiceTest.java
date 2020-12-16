package com.kafka.producer.user.application;

import com.kafka.producer.user.UserModuleUnitTestCase;
import com.kafka.producer.user.domain.UserEmailMother;
import com.kafka.producer.user.domain.UserIdMother;
import com.kafka.producer.user.domain.UserMother;
import com.kafka.producer.user.domain.exception.UserNotFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

final class UserSearcherServiceTest extends UserModuleUnitTestCase {

    private UserSearcherService service;

    @BeforeEach
    protected void setUp() {
        super.setUp();

        service = new UserSearcherService(repository);
    }

    @Test
    void should_find_an_existing_user() {
        var user     = UserMother.random();
        var expected = UserMother.create(user.id(), user.name(), user.email());

        whenFindByIdReturn(user);

        assertThat(service.search(user.id()))
                .isEqualTo(expected);
    }

    @Test
    void should_throw_an_exception_when_user_does_not_exists() {
        whenFindByIdReturnNothing();

        var id = UserIdMother.random();

        assertThatThrownBy(() -> service.search(id))
                .isInstanceOf(UserNotFound.class)
                .hasMessageContaining("user <%s> not found", id.value());
    }

    @Test
    void should_find_an_existing_user_by_email() {
        var user     = UserMother.random();
        var expected = UserMother.create(user.id(), user.name(), user.email());

        whenFindByEmailReturn(user);

        assertThat(service.search(user.email()))
                .isEqualTo(expected);
    }

    @Test
    void should_throw_an_exception_when_user_does_not_exists_by_email() {
        whenFindByEmailReturnNothing();

        var email = UserEmailMother.random();

        assertThatThrownBy(() -> service.search(email))
                .isInstanceOf(UserNotFound.class)
                .hasMessageContaining("user <%s> not found", email.value());
    }

}
