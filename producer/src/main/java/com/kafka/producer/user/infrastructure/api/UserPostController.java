package com.kafka.producer.user.infrastructure.api;

import com.kafka.producer.user.application.UserCreatorService;
import com.kafka.producer.user.domain.UserEmail;
import com.kafka.producer.user.domain.UserName;
import com.kafka.producer.user.infrastructure.api.request.UserCreateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserPostController {

    private final UserCreatorService userCreatorService;

    @PostMapping("/users")
    public ResponseEntity<Void> saveUser(
            @RequestBody UserCreateRequest userCreateRequest) {
        log.info("Received create user request");

        var user = userCreatorService.create(new UserName(userCreateRequest.getName()), new UserEmail(userCreateRequest.getEmail()));

        return ResponseEntity.created(URI.create("/users" + user.id().value())).build();
    }

}
