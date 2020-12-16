package com.kafka.producer.user.infrastructure.persistence;

import com.kafka.producer.user.UserModuleInfrastructureTestCase;
import com.kafka.producer.user.domain.UserEmailMother;
import com.kafka.producer.user.domain.UserIdMother;
import com.kafka.producer.user.domain.UserMother;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

final class InMemoryCourseRepositoryTest extends UserModuleInfrastructureTestCase {

    @Test
    void should_save_a_user() {
        var user = UserMother.random();

        assertThatCode(() -> inMemoryUserRepository.saveOrUpdate(user))
                .doesNotThrowAnyException();
    }

    @Test
    void should_return_an_existing_user() {
        var user = UserMother.random();

        inMemoryUserRepository.saveOrUpdate(user);

        assertThat(inMemoryUserRepository.findById(user.id()))
                .isEqualTo(Optional.of(user));

        assertThat(inMemoryUserRepository.findByEmail(user.email()))
                .isEqualTo(Optional.of(user));
    }

    @Test
    void should_not_return_a_non_existing_user() {
        assertThat(inMemoryUserRepository.findById(UserIdMother.random()))
                .isNotPresent();

        assertThat(inMemoryUserRepository.findByEmail(UserEmailMother.random()))
                .isNotPresent();
    }

    @Test
    void should_delete_a_existing_user_by_id() {
        var user = UserMother.random();

        inMemoryUserRepository.saveOrUpdate(user);

        assertThatCode(() -> inMemoryUserRepository.delete(user.id()))
                .doesNotThrowAnyException();

        assertThat(inMemoryUserRepository.findById(user.id()))
                .isNotPresent();
        assertThat(inMemoryUserRepository.findByEmail(user.email()))
                .isNotPresent();
    }

    @Test
    void should_delete_a_existing_user() {
        var user = UserMother.random();

        inMemoryUserRepository.saveOrUpdate(user);

        assertThatCode(() -> inMemoryUserRepository.delete(user))
                .doesNotThrowAnyException();

        assertThat(inMemoryUserRepository.findById(user.id()))
                .isNotPresent();
        assertThat(inMemoryUserRepository.findByEmail(user.email()))
                .isNotPresent();
    }

}
