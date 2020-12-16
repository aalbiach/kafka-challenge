package com.kafka.producer.user.application;

import com.kafka.producer.shared.domain.UuidGenerator;
import com.kafka.producer.shared.domain.bus.EventBus;
import com.kafka.producer.user.domain.*;
import com.kafka.producer.user.domain.exception.UserExists;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserCreatorService {

    private final UserRepository repository;
    private final UuidGenerator  generator;
    private final EventBus       eventBus;

    public User create(@NonNull UserName name, @NonNull UserEmail email) {
        log.info("Creating new user. [name='{}', email='{}']", name.value(), email.value());

        repository.findByEmail(email).ifPresent(user -> {
            throw new UserExists(email);
        });

        var user = User.create(new UserId(generator.generate()), name, email);

        repository.saveOrUpdate(user);
        eventBus.publish(user.pullDomainEvents());

        return user;
    }

}
