package com.kafka.producer.shared.domain;

public final class IntegerMother {

    public static Integer random() {
        return MotherCreator.random().number().randomDigit();
    }

}
