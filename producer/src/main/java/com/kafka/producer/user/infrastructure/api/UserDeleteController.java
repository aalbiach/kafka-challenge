package com.kafka.producer.user.infrastructure.api;

import com.kafka.producer.user.application.UserDeleterService;
import com.kafka.producer.user.domain.UserId;
import com.kafka.producer.user.infrastructure.api.response.UserUpdateResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserDeleteController {

    private final UserDeleterService userDeleterService;

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<UserUpdateResponse> deleteUser(
            @PathVariable String userId) {
        log.info("Received delete user request");

        userDeleterService.delete(new UserId(userId));

        return ResponseEntity.noContent().build();
    }

}
