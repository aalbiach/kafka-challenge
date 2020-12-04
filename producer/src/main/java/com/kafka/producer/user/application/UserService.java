package com.kafka.producer.user.application;

import com.kafka.producer.shared.domain.UuidGenerator;
import com.kafka.producer.shared.domain.bus.EventBus;
import com.kafka.producer.user.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    private final UuidGenerator generator;

    private final EventBus eventBus;

    public User create(String name, String email) {
        log.info("Creating new user. [name='{}', email='{}']", name, email);

        UserId userId = new UserId(generator.generate());
        UserName userName = new UserName(name);
        UserEmail userEmail = new UserEmail(email);

        User user = User.create(userId, userName, userEmail);

        repository.saveOrUpdate(user);
        eventBus.publish(user.pullDomainEvents());

        return user;
    }

    public Optional<User> search(String id) {
        log.info("Search user. [id='{}']", id);

        return repository.findById(id);
    }

    public Optional<User> update(String id, String name, String email) {
        log.info("Updating user. [id='{}', name='{}', email='{}']", id, name, email);

        Optional<User> optionalUser = repository.findById(id);

        optionalUser.ifPresent(user -> {
            user.updateName(new UserName(name));
            user.updateEmail(new UserEmail(email));

            repository.saveOrUpdate(user);
            eventBus.publish(user.pullDomainEvents());
        });

        return optionalUser;
    }

    public void delete(String id) {
        Optional<User> optionalUser = repository.findById(id);

        optionalUser.ifPresent(user -> {
            log.info("Deleting user. [id='{}', name='{}', email='{}']", id, user.name().value(), user.email().value());

            user.delete();

            repository.deleteById(id);
            eventBus.publish(user.pullDomainEvents());
        });
    }

}
