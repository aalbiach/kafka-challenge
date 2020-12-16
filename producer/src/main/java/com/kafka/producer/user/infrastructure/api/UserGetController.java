package com.kafka.producer.user.infrastructure.api;

import com.kafka.producer.user.application.UserSearcherService;
import com.kafka.producer.user.domain.UserId;
import com.kafka.producer.user.infrastructure.api.response.UserSearchResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserGetController {

    private final UserSearcherService userSearcherService;

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserSearchResponse> getUser(
            @PathVariable String userId) {
        log.info("Received get user request");

        var user = userSearcherService.search(new UserId(userId));

        return ResponseEntity.ok(UserSearchResponse.fromUser(user));
    }

}
