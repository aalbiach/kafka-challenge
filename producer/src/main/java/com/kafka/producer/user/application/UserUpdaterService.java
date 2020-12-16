package com.kafka.producer.user.application;

import com.kafka.producer.shared.domain.bus.EventBus;
import com.kafka.producer.user.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserUpdaterService {

    private final UserRepository      repository;
    private final EventBus            eventBus;
    private final UserSearcherService searcherService;

    public User update(@NonNull UserId id, @NonNull UserName name, @NonNull UserEmail email) {
        var user = searcherService.search(id);

        log.info("Updating user. [id='{}', name='{}', email='{}']", id.value(), name.value(), email.value());

        user.updateName(name);
        user.updateEmail(email);

        repository.saveOrUpdate(user);
        eventBus.publish(user.pullDomainEvents());

        return user;
    }

}
