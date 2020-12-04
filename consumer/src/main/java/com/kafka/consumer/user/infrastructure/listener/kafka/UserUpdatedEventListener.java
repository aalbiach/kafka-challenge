package com.kafka.consumer.user.infrastructure.listener.kafka;

import com.kafka.consumer.shared.infrastructure.DomainEventConverter;
import com.kafka.consumer.user.application.UserService;
import com.kafka.consumer.user.domain.event.UserUpdatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Component
@SuppressWarnings({"rawtypes", "unchecked"})
public class UserUpdatedEventListener extends BaseEventListener {

    static final String TOPIC = TOPIC_PREFIX + ".user.updated";
    static final String GROUP = TOPIC + ".group";

    private final UserService userService;

    public UserUpdatedEventListener(
            UserService userService,
            Set<DomainEventConverter> domainEventConverters) {
        super(domainEventConverters);
        this.userService = userService;
    }

    @KafkaListener(topics = TOPIC, groupId = GROUP)
    public void listen(ConsumerRecord<String, GenericRecord> event) {
        log.info("Received user updated event. [key='{}', event={}]", event.key(), event.value());

        convert(event.value(), domainEventConverter -> {
            var userUpdatedEvent = (UserUpdatedEvent) domainEventConverter.convert(event.value());
            userService.onUserUpdated(userUpdatedEvent);
        });
    }

}
