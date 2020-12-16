package com.kafka.producer.user.application;

import com.kafka.producer.user.UserModuleUnitTestCase;
import com.kafka.producer.user.domain.UserMother;
import com.kafka.producer.user.domain.event.UserCreatedDomainEventMother;
import com.kafka.producer.user.domain.exception.UserExists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

final class UserCreatorServiceTest extends UserModuleUnitTestCase {

    private UserCreatorService service;

    @BeforeEach
    protected void setUp() {
        super.setUp();

        service = new UserCreatorService(repository, uuidGenerator, eventBus);
    }

    @Test
    void should_create_a_valid_user() {
        var user        = UserMother.random();
        var domainEvent = UserCreatedDomainEventMother.fromUser(user);

        whenGenerateUuidReturn(user.id().value());

        service.create(user.name(), user.email());

        verifySaveOrUpdateCalled(user);
        verifyPublishCalled(domainEvent);
    }

    @Test
    void should_not_create_a_valid_user_that_already_exists() {
        var user = UserMother.random();

        whenFindByEmailReturn(user);

        assertThatThrownBy(() -> service.create(user.name(), user.email()))
                .isInstanceOf(UserExists.class)
                .hasMessageContaining("user <%s> already exists", user.email().value());

        verifySaveOrUpdateNotCalled();
        verifyPublishNotCalled();
    }

}
