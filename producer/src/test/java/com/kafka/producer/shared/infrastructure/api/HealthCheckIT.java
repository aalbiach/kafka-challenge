package com.kafka.producer.shared.infrastructure.api;

import com.kafka.producer.ContextMockMvcTestCase;
import org.junit.jupiter.api.Test;

import static org.springframework.http.HttpStatus.OK;

final class HealthCheckIT extends ContextMockMvcTestCase {

    @Test
    void should_check_the_app_is_working_ok() throws Exception {
        var expectedResponse = "{\"status\":\"UP\"}";
        assertGetResponse("/actuator/health", OK, expectedResponse);
    }

}
