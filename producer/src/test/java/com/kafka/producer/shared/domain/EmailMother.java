package com.kafka.producer.shared.domain;

public final class EmailMother {

    public static String random() {
        return MotherCreator.random().internet().emailAddress();
    }

}
