package com.kafka.consumer.shared.infrastructure;

public interface EventConverter<IN, OUT> {

    OUT convert(IN event);

}
