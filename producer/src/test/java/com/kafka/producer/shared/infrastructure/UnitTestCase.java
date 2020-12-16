package com.kafka.producer.shared.infrastructure;

import com.kafka.producer.shared.domain.UuidGenerator;
import com.kafka.producer.shared.domain.bus.EventBus;
import com.kafka.producer.shared.domain.bus.event.DomainEvent;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.mockito.Mockito.*;

public abstract class UnitTestCase {

    protected EventBus      eventBus;
    protected UuidGenerator uuidGenerator;

    @BeforeEach
    protected void setUp() {
        eventBus      = mock(EventBus.class);
        uuidGenerator = mock(UuidGenerator.class);
    }

    protected void verifyPublishCalled(DomainEvent... domainEvents) {
        verifyPublishCalled(List.of(domainEvents));
    }

    protected void verifyPublishCalled(List<DomainEvent> domainEvents) {
        verify(eventBus, atLeastOnce())
                .publish(domainEvents);
    }

    protected void verifyPublishNotCalled() {
        verify(eventBus, never())
                .publish(anyList());
    }

    protected void whenGenerateUuidReturn(String uuid) {
        when(uuidGenerator.generate())
                .thenReturn(uuid);
    }

    protected void whenGenerateUuidReturn(String uuid, String... others) {
        when(uuidGenerator.generate())
                .thenReturn(uuid, others);
    }

}
