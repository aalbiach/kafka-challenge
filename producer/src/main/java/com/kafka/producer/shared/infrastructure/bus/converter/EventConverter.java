package com.kafka.producer.shared.infrastructure.bus.converter;

public interface EventConverter<IN, OUT> {

    OUT convert(IN event);

}
