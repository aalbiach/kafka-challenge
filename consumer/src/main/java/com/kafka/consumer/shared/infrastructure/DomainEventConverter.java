package com.kafka.consumer.shared.infrastructure;

import com.kafka.consumer.shared.domain.bus.event.DomainEvent;
import org.apache.avro.generic.GenericRecord;

public interface DomainEventConverter<IN extends GenericRecord, OUT extends DomainEvent>
        extends EventConverter<IN, OUT> {

    boolean canApply(GenericRecord event);

}
