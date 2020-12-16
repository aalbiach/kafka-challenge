package com.kafka.producer;

import com.kafka.producer.shared.domain.bus.EventBus;
import com.kafka.producer.shared.domain.bus.event.DomainEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = KafkaProducerApplication.class)
@AutoConfigureMockMvc
public abstract class ContextMockMvcTestCase {

    @Autowired
    private MockMvc  mockMvc;
    @Autowired
    private EventBus eventBus;

    protected void assertGetResponse(
            String endpoint,
            HttpStatus expectedStatusCode,
            String expectedResponse
    ) throws Exception {
        var response = expectedResponse.isEmpty()
                ? content().string("")
                : content().json(expectedResponse);

        mockMvc.perform(get(endpoint).accept(APPLICATION_JSON))
               .andExpect(status().is(expectedStatusCode.value()))
               .andExpect(response);
    }

    protected void assertDeleteResponse(
            String endpoint,
            HttpStatus expectedStatusCode
    ) throws Exception {
        mockMvc.perform(delete(endpoint).accept(APPLICATION_JSON))
               .andExpect(status().is(expectedStatusCode.value()))
               .andExpect(content().string(""));
    }

    protected void assertDeleteResponse(
            String endpoint,
            HttpStatus expectedStatusCode,
            String expectedResponse
    ) throws Exception {
        var response = expectedResponse.isEmpty()
                ? content().string("")
                : content().json(expectedResponse);

        mockMvc.perform(delete(endpoint).accept(APPLICATION_JSON))
               .andExpect(status().is(expectedStatusCode.value()))
               .andExpect(response);
    }

    protected void assertRequestWithBody(
            HttpMethod method,
            String endpoint,
            String body,
            HttpStatus expectedStatusCode
    ) throws Exception {
        mockMvc.perform(request(method, endpoint).contentType(APPLICATION_JSON).accept(APPLICATION_JSON).content(body))
               .andExpect(status().is(expectedStatusCode.value()))
               .andExpect(content().string(""));
    }

    protected void assertRequestWithBody(
            HttpMethod method,
            String endpoint,
            String body,
            HttpStatus expectedStatusCode,
            String expectedResponse
    ) throws Exception {
        var response = expectedResponse.isEmpty()
                ? content().string("")
                : content().json(expectedResponse);

        mockMvc.perform(request(method, endpoint).contentType(APPLICATION_JSON).accept(APPLICATION_JSON).content(body))
               .andExpect(status().is(expectedStatusCode.value()))
               .andExpect(response);
    }

    protected void givenISendEventsToTheBus(DomainEvent... domainEvents) {
        eventBus.publish(Arrays.asList(domainEvents));
    }

}
