package com.kafka.producer.shared.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public abstract class StringValueObject {

    @Accessors(fluent = true)
    private final String value;

}
