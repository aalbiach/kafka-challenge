package com.kafka.consumer.user.infrastructure.listener.kafka;

import com.kafka.consumer.shared.infrastructure.DomainEventConverter;
import lombok.RequiredArgsConstructor;
import org.apache.avro.generic.GenericRecord;

import java.util.Set;
import java.util.function.Consumer;

@RequiredArgsConstructor
@SuppressWarnings("rawtypes")
public class BaseEventListener {

    protected static final String TOPIC_PREFIX = "queue.domain-events";

    private final Set<DomainEventConverter> domainEventConverters;

    protected void convert(GenericRecord genericRecord, Consumer<DomainEventConverter> action) {
        domainEventConverters.stream()
                .filter(domainEventConverter -> domainEventConverter.canApply(genericRecord))
                .forEach(action);
    }

}
