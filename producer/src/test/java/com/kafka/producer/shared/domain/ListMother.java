package com.kafka.producer.shared.domain;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class ListMother {

    public static <T> List<T> create(Integer size, Supplier<T> creator) {
        return IntStream.range(0, size)
                        .mapToObj(i -> creator.get())
                        .collect(Collectors.toList());
    }

    public static <T> List<T> random(Supplier<T> creator) {
        return create(IntegerMother.random(), creator);
    }

    public static <T> List<T> one(T element) {
        return Collections.singletonList(element);
    }

}
