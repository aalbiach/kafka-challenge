package com.kafka.consumer.user.infrastructure.listener;

import com.kafka.consumer.user.application.UserService;
import com.kafka.consumer.user.domain.event.UserCreatedEvent;
import com.kafka.consumer.user.domain.event.UserDeletedEvent;
import com.kafka.consumer.user.domain.event.UserUpdatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaUserEventListener {

    private static final String TOPIC_PREFIX = "queue.domain-events";

    private static final String TOPIC_USER_DELETED = TOPIC_PREFIX + ".user.deleted";
    private static final String TOPIC_USER_UPDATED = TOPIC_PREFIX + ".user.updated";
    private static final String TOPIC_USER_CREATED = TOPIC_PREFIX + ".user.created";

    private static final String GROUP_USER_DELETED = TOPIC_USER_DELETED + ".group";
    private static final String GROUP_USER_UPDATED = TOPIC_USER_UPDATED + ".group";
    private static final String GROUP_USER_CREATED = TOPIC_USER_CREATED + ".group";

    private final UserService userService;

    @KafkaListener(topics = TOPIC_USER_CREATED, groupId = GROUP_USER_DELETED/*, containerFactory = "kafkaListenerContainerFactory"*/)
    public void listen(@Payload UserCreatedEvent event, @Header(name = KafkaHeaders.RECEIVED_MESSAGE_KEY, required = false) String key) {
        log.info("Received user created event. [key='{}', event={}]", key, event);
        userService.onUserCreated(event);
    }

    @KafkaListener(topics = TOPIC_USER_UPDATED, groupId = GROUP_USER_UPDATED/*, containerFactory = "kafkaListenerContainerFactory"*/)
    public void listen(@Payload UserUpdatedEvent event, @Header(name = KafkaHeaders.RECEIVED_MESSAGE_KEY, required = false) String key) {
        log.info("Received user updated event. [key='{}', event={}]", key, event);
        userService.onUserUpdated(event);
    }

    @KafkaListener(topics = TOPIC_USER_DELETED, groupId = GROUP_USER_CREATED/*, containerFactory = "kafkaListenerContainerFactory"*/)
    public void listen(@Payload UserDeletedEvent event, @Header(name = KafkaHeaders.RECEIVED_MESSAGE_KEY, required = false) String key) {
        log.info("Received user deleted event. [key='{}', event={}]", key, event);
        userService.onUserDeleted(event);
    }

}
