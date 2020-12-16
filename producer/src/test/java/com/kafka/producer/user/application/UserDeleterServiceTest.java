package com.kafka.producer.user.application;

import com.kafka.producer.user.UserModuleUnitTestCase;
import com.kafka.producer.user.domain.UserIdMother;
import com.kafka.producer.user.domain.UserMother;
import com.kafka.producer.user.domain.event.UserDeletedDomainEventMother;
import com.kafka.producer.user.domain.exception.UserNotFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

final class UserDeleterServiceTest extends UserModuleUnitTestCase {

    private UserDeleterService service;

    @BeforeEach
    protected void setUp() {
        super.setUp();

        var searcherService = new UserSearcherService(repository);
        service = new UserDeleterService(repository, eventBus, searcherService);
    }

    @Test
    void should_delete_an_existing_user() {
        var user         = UserMother.random();
        var deletedEvent = UserDeletedDomainEventMother.create(user.id(), user.name(), user.email(), user.active());

        whenFindByIdReturn(user);

        assertThatCode(() -> service.delete(user.id()))
                .doesNotThrowAnyException();

        verifyDeleteCalled(user.id());
        verifyPublishCalled(deletedEvent);
    }

    @Test
    void should_throw_an_exception_when_user_does_not_exists() {
        whenFindByIdReturnNothing();

        var id = UserIdMother.random();

        assertThatThrownBy(() -> service.delete(id))
                .isInstanceOf(UserNotFound.class)
                .hasMessageContaining("user <%s> not found", id.value());
    }

}
