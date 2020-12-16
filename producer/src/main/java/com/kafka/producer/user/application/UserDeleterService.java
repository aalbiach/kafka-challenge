package com.kafka.producer.user.application;

import com.kafka.producer.shared.domain.bus.EventBus;
import com.kafka.producer.user.domain.UserId;
import com.kafka.producer.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserDeleterService {

    private final UserRepository      repository;
    private final EventBus            eventBus;
    private final UserSearcherService searcherService;

    public void delete(@NonNull UserId id) {
        var user = searcherService.search(id);

        log.info("Deleting user. [id='{}', name='{}', email='{}']", id.value(), user.name().value(), user.email().value());

        user.delete();

        repository.delete(id);
        eventBus.publish(user.pullDomainEvents());
    }

}
