package com.kafka.producer.user.infrastructure.api;

import com.kafka.producer.ContextMockMvcTestCase;
import com.kafka.producer.shared.domain.bus.EventBus;
import com.kafka.producer.user.domain.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

final class UserPostControllerIT extends ContextMockMvcTestCase {

    @Autowired
    private UserRepository userRepository;

    @MockBean
    private EventBus eventBus;

    @BeforeEach
    void setUp() {
        var user = UserMother.create(
                UserIdMother.create("00000000-0000-0000-0000-000000000001"),
                UserNameMother.create("Darth Vader"),
                UserEmailMother.create("darth.vader@galacticempire.com"));

        userRepository.saveOrUpdate(user);
    }

    @AfterEach
    void tearDown() {
        userRepository.delete(UserIdMother.create("00000000-0000-0000-0000-000000000001"));
    }

    @Test
    void should_create_a_valid_non_existing_user() throws Exception {
        var body = """
                   {
                       "name": "Anakin Skywalker",
                       "email": "anakin.skywalker@theresistance.com"
                   }""";

        assertRequestWithBody(POST, "/users", body, CREATED);
    }

    @Test
    void should_not_create_a_valid_user_that_already_exists() throws Exception {
        var body = """
                   {
                       "name": "Darth Vader",
                       "email": "darth.vader@galacticempire.com"
                   }""";
        var expectedResponse = """
                               {
                                   "status": 400,
                                   "error": "Bad Request",
                                   "message": "The user <darth.vader@galacticempire.com> already exists"
                               }""";

        assertRequestWithBody(POST, "/users", body, BAD_REQUEST, expectedResponse);
    }

}
