package com.kafka.producer.user;

import com.kafka.producer.shared.infrastructure.UnitTestCase;
import com.kafka.producer.user.domain.User;
import com.kafka.producer.user.domain.UserId;
import com.kafka.producer.user.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;

import java.util.Optional;

import static org.mockito.Mockito.*;

public abstract class UserModuleUnitTestCase extends UnitTestCase {

    protected UserRepository repository;

    @BeforeEach
    protected void setUp() {
        super.setUp();

        repository = mock(UserRepository.class);
    }

    public void whenFindByIdReturn(User user) {
        when(repository.findById(eq(user.id())))
                .thenReturn(Optional.of(user));
    }

    public void whenFindByEmailReturn(User user) {
        when(repository.findByEmail(eq(user.email())))
                .thenReturn(Optional.of(user));
    }

    public void whenFindByIdReturnNothing() {
        when(repository.findById(any()))
                .thenReturn(Optional.empty());
    }

    public void whenFindByEmailReturnNothing() {
        when(repository.findByEmail(any()))
                .thenReturn(Optional.empty());
    }

    public void verifySaveOrUpdateCalled(User user) {
        verify(repository, atLeastOnce())
                .saveOrUpdate(user);
    }

    public void verifySaveOrUpdateNotCalled() {
        verify(repository, never())
                .saveOrUpdate(any());
    }

    public void verifyDeleteCalled(User user) {
        verify(repository).delete(user);
    }

    public void verifyDeleteCalled(UserId userId) {
        verify(repository).delete(userId);
    }

}
