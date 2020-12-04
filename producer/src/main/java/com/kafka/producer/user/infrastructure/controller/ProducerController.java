package com.kafka.producer.user.infrastructure.controller;

import com.kafka.producer.user.application.UserService;
import com.kafka.producer.user.domain.User;
import com.kafka.producer.user.infrastructure.controller.request.UserCreateRequest;
import com.kafka.producer.user.infrastructure.controller.request.UserUpdateRequest;
import com.kafka.producer.user.infrastructure.controller.response.UserCreateResponse;
import com.kafka.producer.user.infrastructure.controller.response.UserUpdateResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class ProducerController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserCreateResponse> saveUser(@RequestBody UserCreateRequest userCreateRequest) {
        log.info("Received create user request");

        User user = userService.create(userCreateRequest.getName(), userCreateRequest.getEmail());

        return ResponseEntity.ok(UserCreateResponse.fromUser(user));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserUpdateResponse> getUser(@PathVariable String userId) {
        log.info("Received get user request");

        Optional<User> optionalUser = userService.search(userId);

        return optionalUser
                .map(user -> ResponseEntity.ok(UserUpdateResponse.fromUser(user)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserUpdateResponse> updateUser(
            @PathVariable String userId,
            @RequestBody UserUpdateRequest userUpdateRequest) {
        log.info("Received update user request");

        Optional<User> optionalUser = userService.update(userId, userUpdateRequest.getName(), userUpdateRequest.getEmail());

        return optionalUser
                .map(user -> ResponseEntity.ok(UserUpdateResponse.fromUser(user)))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<UserUpdateResponse> deleteUser(@PathVariable String userId) {
        log.info("Received delete user request");

        userService.delete(userId);

        return ResponseEntity.noContent().build();
    }

}
