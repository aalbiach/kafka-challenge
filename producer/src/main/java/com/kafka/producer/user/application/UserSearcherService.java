package com.kafka.producer.user.application;

import com.kafka.producer.user.domain.User;
import com.kafka.producer.user.domain.UserEmail;
import com.kafka.producer.user.domain.UserId;
import com.kafka.producer.user.domain.UserRepository;
import com.kafka.producer.user.domain.exception.UserNotFound;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserSearcherService {

    private final UserRepository repository;

    public User search(@NonNull UserId id) {
        log.info("Search user. [id='{}']", id.value());

        return repository.findById(id).orElseThrow(() -> {
            throw new UserNotFound(id);
        });
    }

    public User search(@NonNull UserEmail email) {
        log.info("Search user. [email='{}']", email.value());

        return repository.findByEmail(email).orElseThrow(() -> {
            throw new UserNotFound(email);
        });
    }

}
