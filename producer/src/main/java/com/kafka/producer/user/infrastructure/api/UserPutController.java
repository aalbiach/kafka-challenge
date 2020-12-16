package com.kafka.producer.user.infrastructure.api;

import com.kafka.producer.user.application.UserUpdaterService;
import com.kafka.producer.user.domain.UserEmail;
import com.kafka.producer.user.domain.UserId;
import com.kafka.producer.user.domain.UserName;
import com.kafka.producer.user.infrastructure.api.request.UserUpdateRequest;
import com.kafka.producer.user.infrastructure.api.response.UserUpdateResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserPutController {

    private final UserUpdaterService userUpdaterService;

    @PutMapping("/users/{userId}")
    public ResponseEntity<UserUpdateResponse> updateUser(
            @PathVariable String userId,
            @RequestBody UserUpdateRequest userUpdateRequest) {
        log.info("Received update user request");

        var user = userUpdaterService.update(new UserId(userId), new UserName(userUpdateRequest.getName()), new UserEmail(userUpdateRequest.getEmail()));

        return ResponseEntity.ok(UserUpdateResponse.fromUser(user));
    }

}
