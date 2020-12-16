package com.kafka.producer.user.infrastructure.api;

import com.kafka.producer.ContextMockMvcTestCase;
import com.kafka.producer.user.domain.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

final class UserGetControllerIT extends ContextMockMvcTestCase {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        var user = UserMother.create(
                UserIdMother.create("00000000-0000-0000-0000-000000000001"),
                UserNameMother.create("Anakin Skywalker"),
                UserEmailMother.create("anakin.skywalker@theresistance.com"));

        userRepository.saveOrUpdate(user);
    }

    @AfterEach
    void tearDown() {
        userRepository.delete(UserIdMother.create("00000000-0000-0000-0000-000000000001"));
    }

    @Test
    void should_find_an_existing_user() throws Exception {
        var id = "00000000-0000-0000-0000-000000000001";
        var body = """
                   {
                       "name": "Anakin Skywalker",
                       "email": "anakin.skywalker@theresistance.com"
                   }""";

        assertGetResponse("/users/%s".formatted(id), OK, body);
    }

    @Test
    void should_no_find_a_non_existing_user() throws Exception {
        var id = "00000000-0000-0000-0000-000000000002";
        var expectedResponse = """
                               {
                                   "status": 404,
                                   "error": "Not Found",
                                   "message": "The user <00000000-0000-0000-0000-000000000002> not found"
                               }""";

        assertGetResponse("/users/%s".formatted(id), NOT_FOUND, expectedResponse);
    }

}
