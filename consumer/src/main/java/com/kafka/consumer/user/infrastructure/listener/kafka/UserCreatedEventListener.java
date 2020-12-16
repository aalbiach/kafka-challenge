package com.kafka.consumer.user.infrastructure.listener.kafka;

import com.kafka.consumer.shared.infrastructure.DomainEventConverter;
import com.kafka.consumer.user.application.UserService;
import com.kafka.consumer.user.domain.event.UserCreatedEvent;
import lombok.extern.log4j.Log4j2;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Set;

@Log4j2
@Component
@SuppressWarnings({"rawtypes", "unchecked"})
public class UserCreatedEventListener extends BaseEventListener {

    static final String TOPIC = TOPIC_PREFIX + ".user.created";
    static final String GROUP = TOPIC + ".group";

    private final UserService userService;

    public UserCreatedEventListener(
            UserService userService,
            Set<DomainEventConverter> domainEventConverters) {
        super(domainEventConverters);
        this.userService = userService;
    }

    @KafkaListener(topics = TOPIC, groupId = GROUP)
    public void listen(ConsumerRecord<String, GenericRecord> event) {
        log.info("Received user created event. [key='{}', event={}]", event.key(), event.value());

        convert(event.value(), domainEventConverter -> {
            var userCreatedEvent = (UserCreatedEvent) domainEventConverter.convert(event.value());
            userService.onUserCreated(userCreatedEvent);
        });
    }

}
