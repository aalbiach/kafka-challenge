package com.kafka.producer.shared.infrastructure.bus.converter;

import com.kafka.producer.shared.domain.bus.event.DomainEvent;
import org.apache.avro.specific.SpecificRecord;

public interface DomainEventConverter<IN extends DomainEvent, OUT extends SpecificRecord>
        extends EventConverter<IN, OUT> {

    boolean canApply(DomainEvent event);

}
