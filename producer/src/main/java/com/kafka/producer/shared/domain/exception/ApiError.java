package com.kafka.producer.shared.domain.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Builder
@Data
public class ApiError {

    private OffsetDateTime timestamp;

    private Integer status;

    private String error;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String message;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> errorMessages;

    private String exception;

    private String path;

}
