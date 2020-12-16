package com.kafka.producer.user.application;

import com.kafka.producer.user.UserModuleUnitTestCase;
import com.kafka.producer.user.domain.UserEmailMother;
import com.kafka.producer.user.domain.UserIdMother;
import com.kafka.producer.user.domain.UserMother;
import com.kafka.producer.user.domain.UserNameMother;
import com.kafka.producer.user.domain.event.UserUpdatedDomainEventMother;
import com.kafka.producer.user.domain.exception.UserNotFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

final class UserUpdaterServiceTest extends UserModuleUnitTestCase {

    private UserUpdaterService service;

    @BeforeEach
    protected void setUp() {
        super.setUp();

        var searcherService = new UserSearcherService(repository);
        service = new UserUpdaterService(repository, eventBus, searcherService);
    }

    @Test
    void should_update_an_existing_user() {
        var user              = UserMother.random();
        var expected          = UserMother.create(user.id(), UserNameMother.random(), UserEmailMother.random());
        var updatedNameEvent  = UserUpdatedDomainEventMother.create(user.id(), expected.name(), user.email(), user.active());
        var updatedEmailEvent = UserUpdatedDomainEventMother.create(user.id(), expected.name(), expected.email(), expected.active());

        whenFindByIdReturn(user);

        assertThat(service.update(user.id(), expected.name(), expected.email()))
                .isEqualTo(expected);

        verifySaveOrUpdateCalled(user);
        verifyPublishCalled(updatedNameEvent, updatedEmailEvent);
    }

    @Test
    void should_throw_an_exception_when_user_does_not_exists() {
        whenFindByIdReturnNothing();

        var id = UserIdMother.random();

        assertThatThrownBy(() -> service.update(id, UserNameMother.random(), UserEmailMother.random()))
                .isInstanceOf(UserNotFound.class)
                .hasMessageContaining("user <%s> not found", id.value());
    }

}
