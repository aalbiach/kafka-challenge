package com.kafka.producer.shared.domain;

public final class NameMother {

    public static String random() {
        return MotherCreator.random().name().fullName();
    }

}
