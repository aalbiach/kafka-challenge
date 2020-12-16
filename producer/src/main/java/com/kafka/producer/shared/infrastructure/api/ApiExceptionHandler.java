package com.kafka.producer.shared.infrastructure.api;

import com.kafka.producer.shared.domain.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.OffsetDateTime;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFound.class)
    public ResponseEntity<ApiError> handleNotFoundException(DomainException ex, HttpServletRequest request) {
        var body = buildApiError(ex, request, NOT_FOUND);
        return ResponseEntity.status(NOT_FOUND).body(body);
    }

    @ExceptionHandler(Forbidden.class)
    public ResponseEntity<ApiError> handleForbiddenException(DomainException ex, HttpServletRequest request) {
        var body = buildApiError(ex, request, FORBIDDEN);
        return ResponseEntity.status(FORBIDDEN).body(body);
    }

    @ExceptionHandler(BadRequest.class)
    public ResponseEntity<ApiError> handleBadRequestException(DomainException ex, HttpServletRequest request) {
        var body = buildApiError(ex, request, BAD_REQUEST);
        return ResponseEntity.status(BAD_REQUEST).body(body);
    }

    private ApiError buildApiError(DomainException ex, HttpServletRequest request, HttpStatus status) {
        return ApiError.builder()
                       .timestamp(OffsetDateTime.now())
                       .status(status.value())
                       .error(ex.errorCode())
                       .message(ex.errorMessage())
                       .exception(ex.getClass().getName())
                       .path(request.getRequestURI())
                       .build();
    }

    /*@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = ex.getBindingResult()
                                .getFieldErrors()
                                .stream()
                                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                                .collect(Collectors.toList());

        HttpServletRequest servletRequest = ((ServletWebRequest) request).getRequest();

        ApiError error = buildApiError(ex, servletRequest, status, ex.getMessage());
        error.setErrorMessages(errors);

        return handleExceptionInternal(ex, error, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (body instanceof ApiError) {
            return super.handleExceptionInternal(ex, body, headers, status, request);
        } else {
            HttpServletRequest servletRequest = ((ServletWebRequest) request).getRequest();
            ApiError           error          = buildApiError(ex, servletRequest, status, ex.getMessage());

            return super.handleExceptionInternal(ex, error, headers, status, request);
        }
    }*/

}
