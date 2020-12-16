package com.kafka.consumer.user.application;

import com.kafka.consumer.user.domain.event.UserCreatedEvent;
import com.kafka.consumer.user.domain.event.UserDeletedEvent;
import com.kafka.consumer.user.domain.event.UserUpdatedEvent;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class UserService {

    public void onUserCreated(UserCreatedEvent event) {
        log.info("[onUserCreated] Executing something. [event='{}']", event);
    }

    public void onUserUpdated(UserUpdatedEvent event) {
        log.info("[onUserUpdated] Executing something. [event='{}']", event);
    }

    public void onUserDeleted(UserDeletedEvent event) {
        log.info("[onUserDeleted] Executing something. [event='{}']", event);
    }

}
