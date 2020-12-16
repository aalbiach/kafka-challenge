package com.kafka.producer.shared.domain;

public final class BooleanMother {

    public static Boolean random() {
        return MotherCreator.random().bool().bool();
    }

}
