package com.kafka.producer.shared.domain;

import com.github.javafaker.Faker;

import java.util.Locale;

public final class MotherCreator {

    private final static Faker faker = new Faker(Locale.getDefault(Locale.Category.FORMAT));

    public static Faker random() {
        return faker;
    }

}
