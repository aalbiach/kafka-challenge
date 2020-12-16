package com.kafka.producer.user.infrastructure.api;

import com.kafka.producer.ContextMockMvcTestCase;
import com.kafka.producer.shared.domain.bus.EventBus;
import com.kafka.producer.user.domain.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

final class UserPutControllerIT extends ContextMockMvcTestCase {

    @Autowired
    private UserRepository userRepository;

    @MockBean
    private EventBus eventBus;

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
    void should_update_a_existing_user() throws Exception {
        var id = "00000000-0000-0000-0000-000000000001";
        var body = """
                   {
                       "name": "Darth Vader",
                       "email": "darth.vader@galacticempire.com"
                   }""";
        var expectedResponse = """
                               {
                                   "id":"00000000-0000-0000-0000-000000000001",
                                   "name":"Darth Vader",
                                   "email":"darth.vader@galacticempire.com"
                               }""";


        assertRequestWithBody(PUT, "/users/%s".formatted(id), body, OK, expectedResponse);
    }

    @Test
    void should_not_update_a_non_existing_user() throws Exception {
        var id = "00000000-0000-0000-0000-000000000002";
        var body = """
                   {
                       "name": "Darth Vader",
                       "email": "darth.vader@galacticempire.com"
                   }""";
        var expectedResponse = """
                               {
                                   "status": 404,
                                   "error": "Not Found",
                                   "message": "The user <00000000-0000-0000-0000-000000000002> not found"
                               }""";

        assertRequestWithBody(PUT, "/users/%s".formatted(id), body, NOT_FOUND, expectedResponse);
    }

}
